package com.employee.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Make sure to import the following packages in your code


import com.employee.models.AwsSecrets;
import com.google.gson.Gson;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
public class ApplicationConfig {
    @Value("${aws.accessKeyId}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretkey;


    private Gson gson = new Gson();

    @Bean
    public DataSource dataSource() {
        AwsSecrets secrets = getSecret();
        return DataSourceBuilder
                .create()
                //  .driverClassName("com.mysql.cj.jdbc.driver")
                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/" + "employee")
                .username(secrets.getUsername())
                .password(secrets.getPassword())
                .build();
    }


//    private AwsSecrets getSecret() {
//
//        String secretName = "javatechie-db-credential";
//        String region = "us-east-2";
//
//
//        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretkey)))
//                .build();
//
//        String secret, decodedBinarySecret;
//        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
//                .withSecretId(secretName);
//        GetSecretValueResult getSecretValueResult = null;
//
//        try {
//            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
//        } catch (Exception e) {
//            throw e;
//        }
//        if (getSecretValueResult.getSecretString() != null) {
//            secret = getSecretValueResult.getSecretString();
//            return gson.fromJson(secret, AwsSecrets.class);
//        }
//
//
//        return null;
//    }
    
    
    
    
    
    
    private AwsSecrets getSecret() {

        String secretName = "dbsecret";
        Region region = Region.of("us-east-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region).credentialsProvider(
               		 StaticCredentialsProvider.create(AwsBasicCredentials
               				  .create(accessKey,secretkey))).build();
               
		/*
		 * TextractClient textractClient =
		 * TextractClient.builder().region(Region.of(region)).credentialsProvider(
		 * StaticCredentialsProvider.create(AwsBasicCredentials
		 * .create(accessKey,secretkey))).build();
		 */
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e;
        }
        if (getSecretValueResponse.secretString() != null) {
        	String secret = getSecretValueResponse.secretString();
        	return gson.fromJson(secret, AwsSecrets.class);
        }
        return null;

        // Your code goes here.
    }

}
