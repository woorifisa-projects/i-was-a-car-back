package xyz.iwasacar.api.domain.histories.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.HistoryAdminResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.members.entity.Member;

public interface SaleService {

	CarInfoResponse findCarInfoByCarNumber(String name, String carNumber);

	SaleResponse saveSalesHistory(
		ProductCreateRequest productCreateRequest, List<MultipartFile> carImages, Long memberId
	);

	default boolean guessFakeCar(final ProductCreateRequest productCreateRequest, final Member member) {
		LocalDate today = LocalDate.now();
		return !Objects.equals(productCreateRequest.getMemberName(), member.getName())
			|| productCreateRequest.isOldCar(today)
			|| productCreateRequest.isDistanceTooShort()
			|| productCreateRequest.isBusyCustomer(today)
			|| member.getBirth().plusYears(19).isAfter(today)
			|| !member.getHasLicense();
	}

	PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(Long memberId, Integer page, Integer size);

	SaleHistoryDetailResponse findSaleHistoryDetail(Long saleHistoryId);

	HistoryAdminResponse findMemberInfo(Long productId);

}
