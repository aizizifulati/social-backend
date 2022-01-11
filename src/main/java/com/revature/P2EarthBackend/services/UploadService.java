package com.revature.P2EarthBackend.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.common.io.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
    private final String awsID = System.getenv("AWS_ID");  //going to have to switch to environment variable when implementing
    private final String secretKey = System.getenv("AWS_SECRET_KEY");
    private final String region = "us-east-2";
    private final String bucketName = "aziz-project-01";

    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsID, secretKey);

    AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();


    public String uploadFile(File file, String name) throws IOException {
        //bucket location, URI for the file in the location, file to send

        ObjectMetadata data = new ObjectMetadata();
//        data.setContentType(file.getContentType());
//        data.setContentLength(file.getSize());

        s3Client.putObject(bucketName, "img/" + name, file);
        String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        String url = "https://aziz-project-01.s3.us-east-2.amazonaws.com/img/"+ name;
        return url;
    }

    public String uploadMultiFile(MultipartFile file, String name) throws IOException {
        //bucket location, URI for the file in the location, file to send

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());

        s3Client.putObject(bucketName, "img/" + name, file.getInputStream(), data);
        String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        String url = "https://aziz-project-01.s3.us-east-2.amazonaws.com/img/"+ name;
        return url;
    }


}
