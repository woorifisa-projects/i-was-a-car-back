package xyz.iwasacar.api.domain.members.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.iwasacar.api.common.config.FeignClientBasicConfig;

@FeignClient(name = "members", url = "${api-server-url}", configuration = {FeignClientBasicConfig.class})
public interface MemberClient {

	@GetMapping("/api/v1/members/identification")
	ResponseEntity<Void> retrieveIdentification(
		@RequestParam String name, @RequestParam String rrnf, @RequestParam String rrnb
	);

}
