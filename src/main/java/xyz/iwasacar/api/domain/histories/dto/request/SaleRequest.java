package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaleRequest {

	private String name;
	private Integer price;
	private String info;
	private Integer zipCode;
	private Integer bankId;
	private String address;
	private String addressDetail;
	private String accountNumber;
	private String accountHolder;
	private LocalDateTime deliverySchedule;
	private Integer productId;
	private Integer memberId;

}
