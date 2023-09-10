package xyz.iwasacar.api.common.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageResponse<T> {

	private final List<T> items;
	private final int page;
	private final int totalPage;

	public static <T> PageResponse<T> of(List<T> items, int page, int totalPage) {
		return new PageResponse<>(items, page, totalPage);
	}

	public static <T> PageResponse<T> of(Page<T> page) {
		return new PageResponse<>(page.getContent(), page.getNumber(), page.getTotalPages());
	}

}
