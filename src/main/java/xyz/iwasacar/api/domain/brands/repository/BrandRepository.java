package xyz.iwasacar.api.domain.brands.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.cartypes.exception.CarTypeNotFoundException;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	default Brand getBy(final Long id) {
		return findById(id)
			.orElseThrow(CarTypeNotFoundException::new);
	}

}
