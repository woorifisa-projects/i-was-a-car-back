package xyz.iwasacar.api.domain.histories.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static xyz.iwasacar.api.common.component.AwsS3Uploader.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.component.AwsS3Uploader;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.brands.repository.BrandRepository;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.caroptions.repository.ProductOptionRepository;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.repository.CarTypeRepository;
import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.colors.repository.ColorRepository;
import xyz.iwasacar.api.domain.histories.client.SaleClient;
import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.HistoryAdminResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductImageRepository;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;
import xyz.iwasacar.api.domain.roles.repository.RoleRepository;
import xyz.iwasacar.api.dummy.DtoDummy;

@ExtendWith(MockitoExtension.class)
class DefaultSaleServiceTest {

	@InjectMocks
	DefaultSaleService saleService;

	@Mock
	SaleClient saleClient;
	@Mock
	MemberRepository memberRepository;
	@Mock
	CarTypeRepository carTypeRepository;
	@Mock
	BrandRepository brandRepository;
	@Mock
	LabelRepository labelRepository;
	@Mock
	ColorRepository colorRepository;
	@Mock
	CarOptionRepository carOptionRepository;
	@Mock
	ProductOptionRepository productOptionRepository;
	@Mock
	BankRepository bankRepository;
	@Mock
	ProductRepository productRepository;
	@Mock
	ResourceRepository resourceRepository;
	@Mock
	ProductImageRepository productImageRepository;
	@Mock
	SaleHistoryRepository saleHistoryRepository;
	@Mock
	RoleRepository roleRepository;
	@Mock
	AwsS3Uploader uploader;

	@DisplayName("차량 번호로 차량 정보 조회")
	@Test
	void testFindCarInfoByCarNumber() {
		String name = "SONATA";
		String carNumber = "123가1234";
		CarInfoResponse carInfoResponse = new CarInfoResponse();

		given(saleClient.findCarInfoByCarNumber(name, carNumber)).willReturn(carInfoResponse);

		CarInfoResponse response = saleService.findCarInfoByCarNumber(name, carNumber);

		assertThat(carInfoResponse.getInfo()).isEqualTo(response.getInfo());

		then(saleClient).should(times(1)).findCarInfoByCarNumber(name, carNumber);
	}

	@DisplayName("차량 판매")
	@Test
	void testSaveSalesHistory() {
		ProductCreateRequest request = DtoDummy.productCreateRequest();
		final Long id = 1L;

		Member mockMember = mock(Member.class);
		CarType mockCarType = mock(CarType.class);
		Brand mockBrand = mock(Brand.class);
		Label mockLabel = mock(Label.class);
		Color mockColor = mock(Color.class);
		List<CarOption> carOptions = List.of();
		Product mockProduct = mock(Product.class);
		Role mockRole = mock(Role.class);
		Bank mockBank = mock(Bank.class);
		SaleHistory mockSaleHistory = mock(SaleHistory.class);
		String url = "url";

		lenient().doReturn(id).when(mockProduct).getId();
		lenient().doReturn(url).when(uploader).upload(any(MultipartFile.class), eq(IMAGES));

		given(mockBank.getName()).willReturn("은행");
		given(mockSaleHistory.getBank()).willReturn(mockBank);
		given(memberRepository.getBy(id)).willReturn(mockMember);
		given(carTypeRepository.getBy(id)).willReturn(mockCarType);
		given(brandRepository.getBy(id)).willReturn(mockBrand);
		given(labelRepository.findByName(LabelName.심사중)).willReturn(Optional.of(mockLabel));
		given(colorRepository.getBy(id)).willReturn(mockColor);
		given(carOptionRepository.findByNames(anyMap())).willReturn(carOptions);
		given(productRepository.save(any(Product.class))).willReturn(mockProduct);
		given(productOptionRepository.saveAll(anyList())).willReturn(List.of());
		given(resourceRepository.saveAll(any())).willReturn(List.of());
		given(roleRepository.findByName(RoleName.MEMBER)).willReturn(Optional.of(mockRole));
		given(productImageRepository.saveAll(any())).willReturn(List.of());
		given(saleHistoryRepository.save(any(SaleHistory.class))).willReturn(mockSaleHistory);
		given(bankRepository.getBy(id)).willReturn(mockBank);

		SaleResponse saleResponse = saleService.saveSalesHistory(request, List.of(), id);

		then(memberRepository).should(times(1)).getBy(id);
		then(carTypeRepository).should(times(1)).getBy(id);
		then(brandRepository).should(times(1)).getBy(id);
		then(labelRepository).should(times(1)).findByName(LabelName.심사중);
		then(colorRepository).should(times(1)).getBy(id);
		then(carOptionRepository).should(times(1)).findByNames(anyMap());

		assertThat(saleResponse).isNotNull();
	}

	@DisplayName("특정 회원의 차량 판매 이력 조회")
	@Test
	void testFindSaleHistoriesByMember() {

		Long memberId = 1L;
		int page = 1;
		int size = 8;
		int totalCount = 10;
		List<SaleHistoryResponse> list = List.of(mock(SaleHistoryResponse.class));

		Pageable pageable = PageRequest.of(page, size);
		Page<SaleHistoryResponse> myPage = new PageImpl<>(list, pageable, totalCount);

		given(saleHistoryRepository.findSales(memberId, page, size)).willReturn(myPage);

		PageResponse<SaleHistoryResponse> response = saleService.findSaleHistoriesByMember(memberId, page, size);

		then(saleHistoryRepository).should(times(1)).findSales(memberId, page, size);

		assertThat(response.getPage()).isEqualTo(page);
	}

	@DisplayName("판매정보 상세 조회")
	@Test
	void testFindSaleHistoryDetail() {
		Long saleHistoryId = 1L;
		SaleHistoryDetailResponse response = mock(SaleHistoryDetailResponse.class);

		given(saleHistoryRepository.findSaleDetail(saleHistoryId)).willReturn(response);

		SaleHistoryDetailResponse saleHistoryDetail = saleService.findSaleHistoryDetail(saleHistoryId);

		assertThat(saleHistoryDetail).isEqualTo(response);

		then(saleHistoryRepository).should(times(1)).findSaleDetail(saleHistoryId);
	}

	@DisplayName("관리자 상품 상세 조회")
	@Test
	void testFindMemberInfo() {
		final Long id = 1L;

		Member member = mock(Member.class);
		SaleHistory saleHistory = mock(SaleHistory.class);

		given(member.getName()).willReturn("황동민");
		given(saleHistory.getMember()).willReturn(member);
		given(saleHistory.getMeetingSchedule()).willReturn(LocalDateTime.now());
		given(saleHistory.getAddress()).willReturn("address");
		given(saleHistory.getAddressDetail()).willReturn("address detail");

		given(saleHistoryRepository.findByProductId(id)).willReturn(saleHistory);

		HistoryAdminResponse memberInfo = saleService.findMemberInfo(id);

		assertThat(memberInfo.getMemberName()).isEqualTo(member.getName());
		assertThat(memberInfo.getMeetingSchedule()).isEqualTo(saleHistory.getMeetingSchedule());

		then(saleHistoryRepository).should(times(1)).findByProductId(id);
	}

}
