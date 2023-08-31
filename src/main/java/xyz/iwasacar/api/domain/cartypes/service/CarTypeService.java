package xyz.iwasacar.api.domain.cartypes.service;

import java.util.List;

import xyz.iwasacar.api.domain.cartypes.dto.response.CarTypeResponse;

public interface CarTypeService {

	List<CarTypeResponse> findCarTypes();
}
