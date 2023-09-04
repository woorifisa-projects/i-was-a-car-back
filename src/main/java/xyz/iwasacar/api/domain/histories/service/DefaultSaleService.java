package xyz.iwasacar.api.domain.histories.service;

import static java.util.stream.Collectors.*;
import static xyz.iwasacar.api.common.component.AwsS3Uploader.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.component.AwsS3Uploader;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.exception.BankNotFoundException;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.brands.exception.BrandNotFoundException;
import xyz.iwasacar.api.domain.brands.repository.BrandRepository;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.entity.ProductOption;
import xyz.iwasacar.api.domain.caroptions.exception.CarOptionException;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.caroptions.repository.ProductOptionRepository;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.exception.CarTypeNotFoundException;
import xyz.iwasacar.api.domain.cartypes.repository.CarTypeRepository;
import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.colors.exception.ColorNotFoundException;
import xyz.iwasacar.api.domain.colors.repository.ColorRepository;
import xyz.iwasacar.api.domain.histories.client.SaleClient;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.labels.exception.LabelNotFoundException;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;
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
		final SaleRequest saleRequest, final List<MultipartFile> carImages,
		final MultipartFile performanceCheck, final Long memberId
	) {

		Member member = memberRepository.findById(memberId)
			.orElseThrow(MemberNotFoundException::new);
		CarType carType = carTypeRepository.findById(saleRequest.getCarTypeId())
			.orElseThrow(CarTypeNotFoundException::new);
		Brand brand = brandRepository.findById(saleRequest.getBrandId())
			.orElseThrow(BrandNotFoundException::new);
		Label label = labelRepository.findByName(LabelName.심사대기중)
			.orElseThrow(LabelNotFoundException::new);
		Color color = colorRepository.findById(saleRequest.getColorId())
			.orElseThrow(ColorNotFoundException::new);
		List<CarOption> options = carOptionRepository.findListById(saleRequest.getCarOptions());

		if (options.size() != saleRequest.getCarOptions().size()) {
			throw new CarOptionException();
		}

		String performanceCheckUrl = uploader.upload(performanceCheck, PERFORMANCE_CHECK);
		Resource savedPerformanceCheck =
			resourceRepository.save(new Resource(performanceCheckUrl, performanceCheck.getOriginalFilename()));

		Product product =
			Product.builder()
				.carType(carType)
				.brand(brand)
				.label(label)
				.performanceCheck(savedPerformanceCheck)
				.color(color)
				.name(saleRequest.getCarName())
				.fakeProductStatus(guessFakeCar(saleRequest, member))
				.info(saleRequest.getInfo())
				.transmission(saleRequest.getTransmission())
				.fuel(saleRequest.getFuel())
				.drivingMethod(saleRequest.getDrivingMethod())
				.year(saleRequest.getYear())
				.distance(saleRequest.getDistance())
				.price(saleRequest.getPrice())
				.fuelEfficiency(saleRequest.getFuelEfficiency())
				.displacement(saleRequest.getDisplacement())
				.accidentHistory(saleRequest.getAccidentHistory())
				.inundationHistory(saleRequest.getInundationHistory())
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

		Bank bank = bankRepository.findById(saleRequest.getBankId())
			.orElseThrow(BankNotFoundException::new);

		SaleHistory saleHistory = SaleHistory.builder()
			.product(product)
			.member(member)
			.bank(bank)
			.meetingSchedule(saleRequest.getMeetingSchedule())
			.accountNumber(saleRequest.getAccountNumber())
			.accountHolder(saleRequest.getAccountHolder())
			.zipCode(saleRequest.getZipCode())
			.address(saleRequest.getAddress())
			.addressDetail(saleRequest.getAddressDetail())
			.build();

		List<String> carImageUrls = images.stream()
			.map(Resource::getUrl)
			.collect(toList());

		SaleHistory savedSaleHistory = saleHistoryRepository.save(saleHistory);

		Map<String, List<String>> optionType = CarOption.convertCarOption(options);

		return new SaleResponse(member, product, savedSaleHistory, carImageUrls, optionType);
	}

}
