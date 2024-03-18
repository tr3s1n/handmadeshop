package com.tresin.cvproj.handmade_shop.repository;

import com.tresin.cvproj.handmade_shop.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);
}