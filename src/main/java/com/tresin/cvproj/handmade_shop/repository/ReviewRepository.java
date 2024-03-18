package com.tresin.cvproj.handmade_shop.repository;

import com.tresin.cvproj.handmade_shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
