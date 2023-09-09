package xyz.iwasacar.api.domain.labels;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.labels.dto.response.LabelResponse;
import xyz.iwasacar.api.domain.labels.service.LabelService;

@RestController
@RequestMapping("/api/v1/labels")
@RequiredArgsConstructor
public class LabelController {

	private final LabelService labelService;

	@GetMapping
	public ResponseEntity<CommonResponse<List<LabelResponse>>> findLabels() {
		List<LabelResponse> labels = labelService.findAll();

		return CommonResponse.success(OK, OK.value(), labels);
	}

}
