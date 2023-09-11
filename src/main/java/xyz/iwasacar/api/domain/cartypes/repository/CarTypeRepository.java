package xyz.iwasacar.api.domain.cartypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.exception.CarTypeNotFoundException;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {

	default CarType getBy(final Long id) {
		return findById(id).orElseThrow(CarTypeNotFoundException::new);
	}

}
