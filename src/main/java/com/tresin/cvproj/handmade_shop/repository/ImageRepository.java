package com.tresin.cvproj.handmade_shop.repository;

import com.tresin.cvproj.handmade_shop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface ImageRepository extends JpaRepository<Image, Long> {
}
