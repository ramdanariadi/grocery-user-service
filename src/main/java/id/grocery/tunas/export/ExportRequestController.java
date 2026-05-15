package id.grocery.tunas.export;

import id.grocery.tunas.export.dto.CreateExportRequestDTO;
import id.grocery.tunas.export.dto.GetListRequestExportDTO;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/export")
@AllArgsConstructor
public class ExportRequestController {

    private ExportRequestService exportRequestService;

    @GetMapping("/product")
    public ResponseEntity<Object> sendRequest(HttpServletRequest request, CreateExportRequestDTO.Request requestBody){
        JsonObject customId = new JsonObject(request.getHeader("x-custom-id"));
        requestBody.setUserId(customId.getString("userId"));
        exportRequestService.exportProduct(requestBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<Object> exportStatus(HttpServletRequest request, GetListRequestExportDTO.Request requestBody){
        JsonObject customId = new JsonObject(request.getHeader("x-custom-id"));
        requestBody.setUserId(customId.getString("userId"));
        GetListRequestExportDTO.Response response = exportRequestService.getListRequestExport(requestBody);
        return ResponseEntity.ok(response);
    }
}
