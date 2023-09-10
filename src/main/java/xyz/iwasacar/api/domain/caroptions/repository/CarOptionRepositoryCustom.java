package xyz.iwasacar.api.domain.caroptions.repository;

import java.util.List;
import java.util.Map;

import xyz.iwasacar.api.domain.caroptions.entity.CarOption;

public interface CarOptionRepositoryCustom {

	List<CarOption> findOptionsByProductId(Long productId);

	List<CarOption> findByNames(Map<String, List<String>> options);

}
