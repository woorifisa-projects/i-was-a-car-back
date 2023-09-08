package xyz.iwasacar.api.domain.labels.service;

import java.util.List;

import xyz.iwasacar.api.domain.labels.dto.response.LabelResponse;

public interface LabelService {

	List<LabelResponse> findAll();

}
