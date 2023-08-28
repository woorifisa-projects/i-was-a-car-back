package xyz.iwasacar.api.common.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.p6spy.engine.spy.P6SpyOptions;

/**
 * p6spy 라이브러리를 사용해 콘솔에 나타나는 쿼리 포멧팅
 *
 * @author dongyeol
 */
@Profile({"local", "dev"})
@Configuration
public class P6spyLogMessageFormatConfiguration {

	@PostConstruct
	public void setLogMessageFormat() {
		P6SpyOptions
			.getActiveInstance()
			.setLogMessageFormat(P6spySqlFormatConfiguration.class.getName());
	}

}
