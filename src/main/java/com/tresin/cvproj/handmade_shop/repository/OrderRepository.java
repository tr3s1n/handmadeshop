package com.tresin.cvproj.handmade_shop.repository;

import com.tresin.cvproj.handmade_shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findOrdersByUser_Id(Long userId);
	Optional<Order> findOrderByPaymentId(Long paymentId);
	@Query("SELECT COUNT(o) FROM Order o JOIN o.products p WHERE p.id = :productId")
	Integer countOrdersByProductId(Long productId);

}
