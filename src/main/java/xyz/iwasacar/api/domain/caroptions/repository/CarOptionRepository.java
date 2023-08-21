package xyz.iwasacar.api.domain.caroptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.caroptions.entity.CarOption;

public interface CarOptionRepository extends JpaRepository<CarOption, Long>, CarOptionRepositoryCustom {
}
