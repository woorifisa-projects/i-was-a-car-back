package xyz.iwasacar.api.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
