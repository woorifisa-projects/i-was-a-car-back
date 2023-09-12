package xyz.iwasacar.api.domain.colors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.colors.exception.ColorNotFoundException;

public interface ColorRepository extends JpaRepository<Color, Long> {

	default Color getBy(final Long id) {
		return findById(id).orElseThrow(ColorNotFoundException::new);
	}

}
