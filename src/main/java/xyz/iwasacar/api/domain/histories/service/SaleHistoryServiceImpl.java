package xyz.iwasacar.api.domain.histories.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;

@RequiredArgsConstructor
@Service
public class SaleHistoryServiceImpl implements SaleHistoryService {

	private final SaleHistoryRepository saleHistoryRepository;

	private final ProductRepository productRepository;

	private final ResourceRepository resourceRepository;

	@Override
	public PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(final Long memberId,
		final Integer page,
		final Integer size) {

		Page<SaleHistoryResponse> saleHistories = saleHistoryRepository.findSaleHistories(memberId, page, size);

		return PageResponse.of(saleHistories.getContent(), page, saleHistories.getTotalPages());
	}

	@Override
	public SaleHistoryDetailResponse findSaleHistoryDetail(final Long saleHistoryId) {

		SaleHistory saleHistoryEntity = saleHistoryRepository.findSpecificSaleHistory(saleHistoryId)
			.orElseThrow(IllegalArgumentException::new);

		Long productId = saleHistoryEntity.getProduct().getId();

		Product productEntity = productRepository.findSpecificProduct(productId)
			.orElseThrow(IllegalArgumentException::new);

		ProductImage productImageEntity = resourceRepository.findSpecificProductImage(productId)
			.orElseThrow(IllegalArgumentException::new);

		return SaleHistoryDetailResponse.from(saleHistoryEntity, productEntity, productImageEntity);
	}

}
