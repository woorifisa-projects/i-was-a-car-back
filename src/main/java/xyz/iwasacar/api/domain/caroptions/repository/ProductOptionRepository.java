package xyz.iwasacar.api.domain.caroptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.caroptions.entity.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption, ProductOption.Pk> {
}
