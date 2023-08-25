package xyz.iwasacar.api.domain.brands.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.brands.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
