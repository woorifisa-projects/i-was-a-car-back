package xyz.iwasacar.api.domain.labels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

public interface LabelRepository extends JpaRepository<Label, Long> {

	Optional<Label> findByName(LabelName labelName);

}
