package xyz.iwasacar.api.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * AWS S3 버킷 설정
 *
 * @author dongyeol
 */
@Configuration
public class AwsS3Config {

	@Value("${spring.cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${spring.cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${spring.cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard()
			.withRegion(region)
			.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
			.build();
	}

}
