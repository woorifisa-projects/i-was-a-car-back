package xyz.iwasacar.api.domain.products.repository;

import java.util.Optional;

import xyz.iwasacar.api.domain.products.entity.Product;

public interface ProductRepositoryCustom {

	Optional<Product> findProductDetail(final Long id);

	Optional<Product> findProductDetailAdmin(final Long id);

}
