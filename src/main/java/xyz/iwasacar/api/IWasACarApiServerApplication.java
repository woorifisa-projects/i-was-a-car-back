package xyz.iwasacar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IWasACarApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IWasACarApiServerApplication.class, args);
	}

}
