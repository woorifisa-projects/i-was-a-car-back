package xyz.iwasacar.api.domain.documentitems.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.documentitems.dto.response.DocumentItemResponse;
import xyz.iwasacar.api.domain.documentitems.service.DocumentItemService;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentItemController {

	private final DocumentItemService documentItemService;

	@GetMapping("/{documentId}")
	public ResponseEntity<CommonResponse<List<DocumentItemResponse>>> findDocumentItems(@PathVariable Long documentId) {

		List<DocumentItemResponse> documentItems = documentItemService.findDocumentItems(documentId);

		return CommonResponse.success(OK, OK.value(), documentItems);

	}
}
