package com.tresin.cvproj.handmade_shop.repository;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.specification.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Long categoryId);
	Optional<Product> findByImageId(Long imageId);
	Optional<Product> findByReviewId(Long reviewId);
	List<Product> findByCartId(Long cartId);
	List<Product> findByOrderId(Long orderId);

	@Query("SELECT p FROM Product p WHERE (:specification.criteria IS EMPTY OR p IN :specification.criteria)")
	List<Product> findAllBySpecification(ProductSpecification specification);
}