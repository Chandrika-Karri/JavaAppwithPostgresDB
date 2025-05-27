package com.example.javawebdemo.s3;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.utils.Validate;
import java.nio.file.Paths;


@Service
public class BucketCreation {

    private final String bucketName = "login-details-chandrika-2025";

    private final S3Client s3Client;

    public BucketCreation() {
        this.s3Client = S3Client.builder()

                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.EU_NORTH_1)
                .build();

    }


    public void createBucketIfNotExists() {
        if (!doesBucketExist(bucketName, s3Client)) {
            try {
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(CreateBucketConfiguration.builder()
                            .locationConstraint(BucketLocationConstraint.EU_NORTH_1)
                            .build())
                    .build();
            s3Client.createBucket(bucketRequest);
            System.out.println("Bucket created successfully!" + bucketName);
        }
         catch (S3Exception e)  {
            System.err.println("Error creating bucket " + e.awsErrorDetails().errorMessage());
            throw e;
        }
    } else {
            System.out.println("Bucket " + bucketName + " already exists.");
        }
    }


    public void uploadFileToS3(String localPath, String s3Key) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromFile(Paths.get(localPath)));
        System.out.println("Uploaded " + s3Key + " to bucket " + bucketName);
    }


    public static boolean doesBucketExist(String bucketName, S3Client s3SyncClient) {
        try {
            Validate.notEmpty(bucketName, "The bucket name must not be null or an empty string.", "");
            s3SyncClient.getBucketAcl(r -> r.bucket(bucketName));
            return true;
        } catch (AwsServiceException ase) {
            // A redirect error or an AccessDenied exception means the bucket exists but it's not in this region
            // or we don't have permissions to it.
            if ((ase.statusCode() == HttpStatusCode.MOVED_PERMANENTLY) || "AccessDenied".equals(ase.awsErrorDetails().errorCode())) {
                return true;
            }
            if (ase.statusCode() == HttpStatusCode.NOT_FOUND) {
                return false;
            }
            throw ase;
        }
    }
}



