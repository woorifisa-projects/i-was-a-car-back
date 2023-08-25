package xyz.iwasacar.api.domain.labels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.labels.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
