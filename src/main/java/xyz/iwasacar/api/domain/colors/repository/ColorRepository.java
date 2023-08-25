package xyz.iwasacar.api.domain.colors.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.colors.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
