package xyz.iwasacar.api.domain.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

	default Product getBy(Long id) {
		return findById(id).orElseThrow(ProductNotFound::new);
	}

}
