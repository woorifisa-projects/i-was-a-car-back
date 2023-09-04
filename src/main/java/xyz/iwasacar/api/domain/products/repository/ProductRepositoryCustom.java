package xyz.iwasacar.api.domain.products.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.entity.Product;

public interface ProductRepositoryCustom {

	Page<ProductResponse> findProductsForAdmin(final int page, final int size);

	Optional<Product> findProductDetail(final Long id);

	Optional<Product> findProductDetailForAdmin(final Long id);

}
