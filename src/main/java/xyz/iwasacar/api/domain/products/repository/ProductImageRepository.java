package xyz.iwasacar.api.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.resources.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImage.Pk> {
}
