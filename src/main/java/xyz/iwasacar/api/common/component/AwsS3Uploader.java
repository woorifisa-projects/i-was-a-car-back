package xyz.iwasacar.api.common.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Uploader {

	public static final String IMAGES = "images";
	public static final String PERFORMANCE_CHECK = "performance_check";

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 s3Client;

	public String upload(final MultipartFile file, final String dir) {

		String name = UUID.randomUUID() + "." + file.getContentType();

		// 저장할 파일 이름 설정
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS");
		// "/"를 이용해 파일 하위 폴더 설정
		String s3FileName = String.format("%s/%s-%s", dir, LocalDateTime.now().format(format), name);

		ObjectMetadata objMeta = new ObjectMetadata();
		objMeta.setContentLength(file.getSize());

		try {
			s3Client.putObject(bucket, s3FileName, file.getInputStream(), objMeta);
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return s3Client.getUrl(bucket, s3FileName).toString();
	}

}
