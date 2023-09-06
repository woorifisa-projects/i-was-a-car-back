package xyz.iwasacar.api.domain.histories.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.iwasacar.api.common.config.FeignClientBasicConfig;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;

@FeignClient(name = "sales", url = "${api-server-url}", configuration = {FeignClientBasicConfig.class})
public interface SaleClient {

	@GetMapping("/api/v1/carInfo")
	CarInfoResponse findCarInfoByCarNumber(@RequestParam String name, @RequestParam String carNumber);

}
