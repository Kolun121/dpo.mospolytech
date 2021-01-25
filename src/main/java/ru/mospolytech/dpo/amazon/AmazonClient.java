package ru.mospolytech.dpo.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.util.IOUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.imageio.ImageIO;

@Service
public class AmazonClient {

    private AmazonS3 s3client;

    @Value("${amazon.s3.endpoint}")
    private String endpointUrl;
    @Value("${amazon.s3.bucket-name}")
    private String bucketName;
    @Value("${amazon.s3.access-key}")
    private String accessKey;
    @Value("${amazon.s3.secret-key}")
    private String secretKey;
    
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
.withRegion(Regions.EU_NORTH_1).build();
    }
    
    public String uploadFile(MultipartFile multipartFile, String controllerDir) {
        String resultFilename = "";
//        s3client.getU
//        ObjectMetadata meta = new ObjectMetadata();
//                       meta.setContentType(multipartFile.getContentType());
//                       meta.setContentLength(multipartFile.getSize());
//                       meta.setHeader("filename", fileName);

//        ByteArrayInputStream bis = new ByteArrayInputStream(multipartFile.getBytes());

        
//
//            File outputFile = new File(uploadPath + imageControllerDir + "/" + resultFilename);
//            ImageIO.write(image, "png", outputFile);  

//        TransferManager transferManager = new TransferManager(this.s3client);
//                        transferManager.upload(bucketName, fileName, bis, meta);
                        
                        
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            String uuidFile  = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + "png";

            File outputFile = new File(resultFilename);
            ImageIO.write(image, "png", outputFile);  
            uploadFileTos3bucket(controllerDir + "/" + resultFilename, outputFile);
            outputFile.delete();
        } catch (Exception e) {
//           e.printStackTrace();
        }
        return getUrl(controllerDir + "/" + resultFilename);
    }
    
    private String getUrl(String filename){
        return s3client.getUrl(bucketName, filename).toString();
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        String uuidFile  = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + "png";

        File convFile = new File(resultFilename);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileName, String controllerDir) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, controllerDir + "/" + fileName.substring(fileName.lastIndexOf("/") + 1)));
        return "Successfully deleted";
    }
    
    public byte[] getFile(String key, String controllerFolder) {
        S3Object obj = s3client.getObject(bucketName ,key);
        
        S3ObjectInputStream stream = obj.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(stream);
            obj.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}