package xyz.iwasacar.api.domain.resources.repository;

import java.util.List;
import java.util.Optional;

import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;

public interface ResourceRepositoryCustom {

	List<Resource> findByProductId(Long productId);

	List<ProductImage> findByProducts(Long lastProductId);

	Optional<ProductImage> findSpecificProductImage(Long productId);
}
