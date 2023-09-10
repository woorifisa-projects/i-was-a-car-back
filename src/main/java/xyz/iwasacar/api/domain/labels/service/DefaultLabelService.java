package xyz.iwasacar.api.domain.labels.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.dto.response.LabelResponse;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;

@Service
@RequiredArgsConstructor
public class DefaultLabelService implements LabelService {

	private final LabelRepository labelRepository;

	@Override
	public List<LabelResponse> findAll() {
		return labelRepository.findAll()
			.stream()
			.map(LabelResponse::from)
			.collect(toList());
	}

}
