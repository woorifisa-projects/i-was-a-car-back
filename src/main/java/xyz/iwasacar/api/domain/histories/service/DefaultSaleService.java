package xyz.iwasacar.api.domain.histories.service;

import static java.util.stream.Collectors.*;
import static xyz.iwasacar.api.common.component.AwsS3Uploader.*;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.component.AwsS3Uploader;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.brands.repository.BrandRepository;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.entity.ProductOption;
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
import xyz.iwasacar.api.domain.labels.exception.LabelNotFoundException;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductImageRepository;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;
import xyz.iwasacar.api.domain.roles.exception.RoleNotFoundException;
import xyz.iwasacar.api.domain.roles.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

	private final SaleClient saleClient;
	private final MemberRepository memberRepository;
	private final CarTypeRepository carTypeRepository;
	private final BrandRepository brandRepository;
	private final LabelRepository labelRepository;
	private final ColorRepository colorRepository;
	private final CarOptionRepository carOptionRepository;
	private final ProductOptionRepository productOptionRepository;
	private final BankRepository bankRepository;
	private final ProductRepository productRepository;
	private final ResourceRepository resourceRepository;
	private final ProductImageRepository productImageRepository;
	private final SaleHistoryRepository saleHistoryRepository;
	private final RoleRepository roleRepository;

	private final AwsS3Uploader uploader;

	@Override
	public CarInfoResponse findCarInfoByCarNumber(final String name, final String carNumber) {
		return saleClient.findCarInfoByCarNumber(name, carNumber);
	}

	@Transactional
	@Override
	public SaleResponse saveSalesHistory(
		final ProductCreateRequest productCreateRequest, final List<MultipartFile> carImages, final Long memberId
	) {

		Member member = memberRepository.getBy(memberId);
		CarType carType = carTypeRepository.getBy(productCreateRequest.getCarTypeId());
		Brand brand = brandRepository.getBy(productCreateRequest.getBrandId());
		Label label = labelRepository.findByName(LabelName.심사중).orElseThrow(LabelNotFoundException::new);
		Color color = colorRepository.getBy(productCreateRequest.getColorId());
		List<CarOption> options = carOptionRepository.findByNames(productCreateRequest.getOptions());

		Product product =
			Product.builder()
				.carType(carType)
				.brand(brand)
				.label(label)
				.color(color)
				.name(productCreateRequest.getCarName())
				.fakeProductStatus(guessFakeCar(productCreateRequest, member))
				.info(productCreateRequest.getInfo())
				.transmission(productCreateRequest.getTransmission())
				.fuel(productCreateRequest.getFuel())
				.drivingMethod(productCreateRequest.getDrivingMethod())
				.year(productCreateRequest.getYear())
				.distance(productCreateRequest.getDistance())
				.price(productCreateRequest.getPrice())
				.fuelEfficiency(productCreateRequest.getFuelEfficiency())
				.displacement(productCreateRequest.getDisplacement())
				.accidentHistory(productCreateRequest.getAccidentHistory())
				.inundationHistory(productCreateRequest.getInundationHistory())
				.build();

		Product savedProduct = productRepository.save(product);

		List<ProductOption> productOptions = options.stream()
			.map(o -> new ProductOption(product, o))
			.collect(toList());

		productOptionRepository.saveAll(productOptions);

		List<Resource> images = carImages
			.stream()
			.map(i -> new Resource(uploader.upload(i, IMAGES), i.getOriginalFilename()))
			.collect(toList());

		List<Resource> resources = resourceRepository.saveAll(images);

		Role memberRole = roleRepository.findByName(RoleName.MEMBER)
			.orElseThrow(RoleNotFoundException::new);

		List<ProductImage> productImages = resources.stream()
			.map(r -> new ProductImage(savedProduct, r, memberRole))
			.collect(toList());

		productImageRepository.saveAll(productImages);

		Bank bank = bankRepository.getBy(productCreateRequest.getBankId());

		SaleHistory saleHistory = SaleHistory.builder()
			.product(product)
			.member(member)
			.bank(bank)
			.meetingSchedule(productCreateRequest.getMeetingSchedule())
			.accountNumber(productCreateRequest.getAccountNumber())
			.accountHolder(productCreateRequest.getAccountHolder())
			.zipCode(productCreateRequest.getZipCode())
			.address(productCreateRequest.getAddress())
			.addressDetail(productCreateRequest.getAddressDetail())
			.build();

		List<String> carImageUrls = images.stream()
			.map(Resource::getUrl)
			.collect(toList());

		SaleHistory savedSaleHistory = saleHistoryRepository.save(saleHistory);

		Map<String, List<String>> optionType = CarOption.convertCarOption(options);

		return SaleResponse.from(member, product, savedSaleHistory, carImageUrls, optionType);
	}

	@Override
	public PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(final Long memberId, final Integer page,
		final Integer size) {
		Page<SaleHistoryResponse> sales = saleHistoryRepository.findSales(memberId, page, size);
		return new PageResponse<>(sales.getContent(), page, sales.getTotalPages());
	}

	@Override
	public SaleHistoryDetailResponse findSaleHistoryDetail(final Long saleHistoryId) {

		return saleHistoryRepository.findSaleDetail(saleHistoryId);
	}

	@Override
	public HistoryAdminResponse findMemberInfo(final Long productId) {

		SaleHistory saleHistory = saleHistoryRepository.findByProductId(productId);

		Member member = saleHistory.getMember();

		return HistoryAdminResponse.of(saleHistory, member);
	}

}
