package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ImageApi;
import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController implements ImageApi {

	private final ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@Override
	public ResponseEntity<Image> createImage(@Valid @RequestBody ImageDTO imageDTO) {
		Image createdImage = imageService.createImage(imageDTO.toImage());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
	}

	@Override
	public ResponseEntity<Image> updateImage(@PathVariable Long id, @Valid @RequestBody ImageDTO imageDTO) {
		Image updatedImage = imageService.createImage(imageDTO.toImage());
		Image resultImage = imageService.updateImage(id, updatedImage);
		return ResponseEntity.ok(resultImage);
	}

	@Override
	public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
		imageService.deleteImage(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Image>> getAllImages() {
		return ResponseEntity.ok(imageService.getAllImages());
	}

	@Override
	public ResponseEntity<Image> getImageById(@PathVariable Long id) {
		return ResponseEntity.ok(imageService.getImageById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Image>> getImagesByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(imageService.getImagesByProductId(productId));
	}
}
