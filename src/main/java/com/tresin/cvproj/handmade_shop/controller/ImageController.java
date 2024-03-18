package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ImageApi;
import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
		Image newImage = new Image();
		newImage.setProduct(imageDTO.getProduct());
		newImage.setUrl(imageDTO.getUrl());
		Image createdImage = imageService.createImage(newImage);

		return ResponseEntity.ok(createdImage);
	}

	@Override
	public ResponseEntity<Image> updateImage(@PathVariable Long id, @Valid @RequestBody ImageDTO imageDTO) {
		Image updatedImage = new Image();
		updatedImage.setProduct(imageDTO.getProduct());
		updatedImage.setUrl(imageDTO.getUrl());
		Image resultImage = imageService.updateImage(id, updatedImage);

		if (resultImage != null) {
			return ResponseEntity.ok(resultImage);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
		imageService.deleteImage(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Image>> getAllImages() {
		List<Image> images = imageService.getAllImages();

		return ResponseEntity.ok(images);
	}

	@Override
	public ResponseEntity<Image> getImageById(@PathVariable Long id) {
		return imageService.getImageById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Product> getProductByImageId(@PathVariable Long id) {
		Image image = imageService.getImageById(id).orElse(null);

		if (image != null) {
			Product product = image.getProduct();
			if (product != null) {
				return ResponseEntity.ok(product);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
