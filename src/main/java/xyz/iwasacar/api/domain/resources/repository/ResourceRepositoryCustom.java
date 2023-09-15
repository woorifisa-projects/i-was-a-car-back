package xyz.iwasacar.api.domain.resources.repository;

import java.util.List;

import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;

public interface ResourceRepositoryCustom {

	List<Resource> findByProductId(Long productId);

	List<Resource> findByProductIdAdmin(Long productId);

	List<ProductImage> findByProducts(Long category, String keyword, Long lastProductId);

	List<ProductImage> findBySpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId);

}
