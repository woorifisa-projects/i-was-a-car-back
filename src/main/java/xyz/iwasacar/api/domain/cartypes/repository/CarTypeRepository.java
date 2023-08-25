package xyz.iwasacar.api.domain.cartypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.cartypes.entity.CarType;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
