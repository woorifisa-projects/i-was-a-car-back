package xyz.iwasacar.api.domain.caroptions.repository;

import java.util.List;

import xyz.iwasacar.api.domain.caroptions.entity.CarOption;

public interface CarOptionRepositoryCustom {

	List<CarOption> findOptionsByProductId(Long productId);

}
