package xyz.iwasacar.api.domain.histories.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.members.entity.Member;

public interface SaleService {

	CarInfoResponse findCarInfoByCarNumber(String name, String carNumber);

	SaleResponse saveSalesHistory(
		SaleRequest saleRequest, List<MultipartFile> carImages, MultipartFile performanceCheck, Long memberId
	);

	default boolean guessFakeCar(final SaleRequest saleRequest, final Member member) {
		LocalDate today = LocalDate.now();
		return !Objects.equals(saleRequest.getMemberName(), member.getName())
			|| saleRequest.isOldCar(today)
			|| saleRequest.isDistanceTooShort()
			|| saleRequest.isBusyCustomer(today)
			|| member.getBirth().plusYears(19).isAfter(today)
			|| !member.getHasLicense();
	}

	PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(Long memberId, Integer page, Integer size);

	SaleHistoryDetailResponse findSaleHistoryDetail(Long saleHistoryId);
}
