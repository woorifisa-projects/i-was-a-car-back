package xyz.iwasacar.api.domain.histories.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.exception.BankNotFound;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.histories.dto.request.PurchaseHistoryRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.histories.repository.PurchaseHistoryRepository;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.insurances.exception.InsuranceNotFound;
import xyz.iwasacar.api.domain.insurances.repository.InsuranceRepository;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.labels.exception.LabelNotFoundException;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.loans.entity.Loan;
import xyz.iwasacar.api.domain.loans.exception.LoanNotFound;
import xyz.iwasacar.api.domain.loans.repository.LoanRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultPurchaseService implements PurchaseService {

	private final PurchaseHistoryRepository purchaseHistoryRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final BankRepository bankRepository;
	private final LoanRepository loanRepository;
	private final InsuranceRepository insuranceRepository;
	private final LabelRepository labelRepository;

	@Transactional
	@Override
	public PurchaseHistoryResponse savePurchaseHistory(PurchaseHistoryRequest purchaseHistoryRequest) {

		Member member = memberRepository.findById(purchaseHistoryRequest.getMemberId())
			.orElseThrow(MemberNotFoundException::new);

		Bank bank = bankRepository.findById(purchaseHistoryRequest.getBankId())
			.orElseThrow(BankNotFound::new);

		Loan loan = loanRepository.findById(purchaseHistoryRequest.getLoanId())
			.orElseThrow(LoanNotFound::new);

		Insurance insurance = insuranceRepository.findById(purchaseHistoryRequest.getInsuranceId())
			.orElseThrow(InsuranceNotFound::new);

		// 판매 완료로 라벨 바꿈
		Product product = productRepository.findById(purchaseHistoryRequest.getProductId())
			.orElseThrow(ProductNotFound::new);

		Label label = labelRepository.findByName(LabelName.판매완료)
			.orElseThrow(LabelNotFoundException::new);

		Product newLabelProduct = Product.changeLabel(product, label);

		// 구매 내역 저장
		PurchaseHistory purchaseHistory = PurchaseHistory.builder()
			.member(member)
			.product(newLabelProduct)
			.bank(bank)
			.loan(loan)
			.insurance(insurance)
			.zipCode(
				purchaseHistoryRequest.getZipCode())
			.address(purchaseHistoryRequest.getAddress())
			.addressDetail(
				purchaseHistoryRequest.getAddress())
			.accountNumber(purchaseHistoryRequest.getAccountNumber())
			.accountHolder(
				purchaseHistoryRequest.getAccountHolder())
			.loanAmount(purchaseHistoryRequest.getLoanAmount())
			.period(purchaseHistoryRequest.getPeriod())
			.deliverySchedule(purchaseHistoryRequest.getDeliverySchedule())
			.status(EntityStatus.CREATED).build();

		PurchaseHistory savedpurchaseHistory = purchaseHistoryRepository.save(purchaseHistory);

		PurchaseHistoryResponse purchaseHistoryResponse = PurchaseHistoryResponse.of(savedpurchaseHistory);

		return purchaseHistoryResponse;
	}
}
