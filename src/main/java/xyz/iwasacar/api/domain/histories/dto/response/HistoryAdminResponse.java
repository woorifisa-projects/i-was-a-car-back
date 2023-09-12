package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.members.entity.Member;

@RequiredArgsConstructor
@Getter
public class HistoryAdminResponse {

	private final String memberName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime meetingSchedule;
	private final String address;
	private final String addressDetail;

	public static HistoryAdminResponse of(SaleHistory saleHistory, Member member) {
		return new HistoryAdminResponse(
			member.getName(),
			saleHistory.getMeetingSchedule(),
			saleHistory.getAddress(),
			saleHistory.getAddressDetail()
		);
	}

}
