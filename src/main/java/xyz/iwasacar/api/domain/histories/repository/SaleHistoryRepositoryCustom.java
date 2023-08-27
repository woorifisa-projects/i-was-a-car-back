package xyz.iwasacar.api.domain.histories.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;

public interface SaleHistoryRepositoryCustom {

	Page<SaleHistoryResponse> findSaleHistories(final Long memberId, final Integer page, final Integer size);

	Optional<SaleHistory> findSpecificSaleHistory(final Long saleHistoryId);

}
