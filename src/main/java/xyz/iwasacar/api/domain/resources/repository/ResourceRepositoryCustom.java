package xyz.iwasacar.api.domain.resources.repository;

import java.util.List;

import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;

public interface ResourceRepositoryCustom {

	List<Resource> findByProductId(Long productId);

	List<ProductImage> findByProducts(Long lastProductId);

	List<ProductImage> findBySepcificProducts(Long carType, Integer capital, Integer loan, Long lastProductId);
}
