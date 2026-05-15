package id.grocery.tunas.download;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import id.grocery.tunas.download.dto.DownloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DownloadService {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public DownloadService(AmazonS3 s3Client, @Value("${aws.s3.bucket.name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public byte[] download(DownloadDTO request){
        S3Object object = s3Client.getObject(bucketName, request.getFilename());
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return objectContent.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
