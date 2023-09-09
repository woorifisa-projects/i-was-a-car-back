package xyz.iwasacar.api.domain.labels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.labels.exception.LabelNotFoundException;

public interface LabelRepository extends JpaRepository<Label, Long> {

	Optional<Label> findByName(LabelName labelName);

	default Label getBy(final Long id) {
		return findById(id).orElseThrow(LabelNotFoundException::new);
	}

}
