package xyz.iwasacar.api.common.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {

	private static final long SIZE_1000MB = 1048576000L;

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	/**
	 * location: 업로드된 파일의 임시 저장 공간
	 * fileSizeThreshold: 파일이 메모리에 기록되는 임계값 (default: 0B)
	 * maxRequestSize: 요청의 최대 사이즈 (default: 10MB)
	 * maxFileSize: 파일의 최대 사이즈 (default: 1MB)
	 *
	 * @see <a href="https://gofnrk.tistory.com/36">Spring MultipartFile</a>
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		String location = System.getProperty("user.home") + "/tmp_file";

		MultipartConfigFactory factory = new MultipartConfigFactory();

		factory.setLocation(location);
		factory.setFileSizeThreshold(DataSize.ofBytes(SIZE_1000MB / 100));
		factory.setMaxRequestSize(DataSize.ofBytes(SIZE_1000MB));
		factory.setMaxFileSize(DataSize.ofBytes(SIZE_1000MB));

		return factory.createMultipartConfig();
	}

}
