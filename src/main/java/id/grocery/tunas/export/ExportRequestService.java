package id.grocery.tunas.export;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import id.grocery.tunas.exception.ApiRequestException;
import id.grocery.tunas.export.dto.CreateExportRequestDTO;
import id.grocery.tunas.export.dto.GetListRequestExportDTO;
import id.grocery.tunas.export.repository.ExportRequestPagingAndSortingRepository;
import id.grocery.tunas.export.repository.ExportRequestRepository;
import id.grocery.tunas.product.ProductDAO;
import id.grocery.tunas.user.User;
import id.grocery.tunas.user.UserRepository;
import id.grocery.tunas.utils.ExportUtil;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExportRequestService {

    private AmazonS3 s3Client;
    private ProductDAO productDAO;
    private KafkaTemplate kafkaTemplate;
    private UserRepository userRepository;
    private ExportRequestRepository exportRequestRepository;
    private ExportRequestPagingAndSortingRepository exportRequestPagingAndSortingRepository;

    @Value("${aws.s3.bucket.name}")
    private Optional<String> s3Bucket;

    @Value("${messaging.outgoing.export-report-request.topic}")
    private Optional<String> requestExportTopicName;

    @Value("${messaging.outgoing.export-report-result.topic}")
    private Optional<String> resultExportTopicName;

    private final Logger LOGGER = LoggerFactory.getLogger(ExportRequestService.class);

    @KafkaListener(topics = ("${messaging.outgoing.export-report-request.topic}"), groupId = "foo")
    public void listenRequestExport(String payload){
        LOGGER.info("here is message getting from kafka stream : {}",payload);
                Query allProducts = productDAO.getAllProducts(false);
        JsonObject request = new JsonObject(payload);
        allProducts.setFirstResult(request.getInteger("pageIndex") * request.getInteger("pageSize")).setMaxResults(request.getInteger("pageSize"));
        List<Object[]> resultList = allProducts.getResultList();

        String filename = UUID.randomUUID() + ".xls";
        String[] header = new String[]{"ID", "Shop Id", "Shop Name", "Price", "Weight", "Category", "Per Unit", "Description", "Image URL", "Product Name"};
        String sheetName = "Sheet1";
        byte[] bytes = ExportUtil.exportExel(filename, sheetName, header, resultList.toArray(Object[][]::new));

        CreateExportRequestDTO.Result result = new CreateExportRequestDTO.Result();
        result.setRequestId(request.getString("requestId"));
        try{
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("application/excel");
            objectMetadata.setContentLength(bytes.length);
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    s3Bucket.orElse(""), filename, new ByteArrayInputStream(bytes), objectMetadata);
            s3Client.putObject(putObjectRequest);

            result.setFilename(filename);
            result.setStatus(ExportRequest.EXPORT_STATUS_SUCCESS);
        }catch (Exception e){
            LOGGER.info("putObjectException : {}", e.getMessage());
            result.setStatus(ExportRequest.EXPORT_STATUS_FAILED);
        }finally {
            kafkaTemplate.send(resultExportTopicName.orElse(""), JsonObject.mapFrom(result).encode());
        }
    }

    @KafkaListener(topics = "${messaging.outgoing.export-report-result.topic}", groupId = "foo")
    public void listerExportResult(String payload){
        JsonObject result = new JsonObject(payload);
        Optional<ExportRequest> requestExportOptional = exportRequestRepository.findById(UUID.fromString(result.getString("requestId")));
        if(requestExportOptional.isPresent()){
            ExportRequest exportRequest = requestExportOptional.get();
            exportRequest.setFileName(result.getString("filename"));
            exportRequest.setStatus(result.getString("status"));
            exportRequestRepository.save(exportRequest);
        }
    }

    public void exportProduct(CreateExportRequestDTO.Request request){
        Optional<User> byId = userRepository.findById(UUID.fromString(request.getUserId()));
        if(byId.isEmpty()){
            throw new ApiRequestException(ApiRequestException.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
        }

        ExportRequest exportRequest = new ExportRequest();
        exportRequest.setUser(byId.get());
        exportRequest.setStatus(ExportRequest.EXPORT_STATUS_NEW);
        exportRequestRepository.save(exportRequest);

        request.setRequestId(exportRequest.getId().toString());
        request.setUserId(request.getUserId());
        kafkaTemplate.send(requestExportTopicName.orElse(""), JsonObject.mapFrom(request).encode());
    }

    public GetListRequestExportDTO.Response getListRequestExport(GetListRequestExportDTO.Request request){
        PageRequest pageRequest = PageRequest.of(request.getPageIndex() * request.getPageSize(), request.getPageSize());
        List<ExportRequest> allByUser = exportRequestPagingAndSortingRepository.findAllByUserId(UUID.fromString(request.getUserId()), pageRequest);
        List<GetListRequestExportDTO.SimpleRequestExport> data = allByUser
                .stream()
                .map(exportRequest -> new GetListRequestExportDTO.SimpleRequestExport(exportRequest.getId(), exportRequest.getCreatedAt().toString(), exportRequest.getFileName(), exportRequest.getStatus()))
                .collect(Collectors.toList());
        GetListRequestExportDTO.Response response = new GetListRequestExportDTO.Response();
        response.setData(data);
        response.setTotalData(exportRequestPagingAndSortingRepository.findAllByUserId(UUID.fromString(request.getUserId()), Pageable.unpaged()).size());
        response.setPageSize(request.getPageSize());
        request.setPageIndex(request.getPageIndex());
        return response;
    }
}
