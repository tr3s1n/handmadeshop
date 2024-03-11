package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

	@InjectMocks
	private ImageService imageService;

	@Mock
	private ImageRepository imageRepository;

	@Test
	public void testCreateImage() {
		Image image = new Image(new Product("Product 1", 10.99), "https://picsum.photos/200");
		when(imageRepository.save(any(Image.class))).thenReturn(image);
		Image createdImage = imageService.createImage(image);

		assertNotNull(createdImage);
	}

	@Test
	public void testDeleteImage() {
		long imageIdToDelete = 1L;
		Image imageToDelete = new Image(new Product("Product 1", 10.99), "https://picsum.photos/200");
		when(imageRepository.findById(imageIdToDelete)).thenReturn(Optional.of(imageToDelete));
		imageService.deleteImage(imageIdToDelete);

		verify(imageRepository, times(1)).deleteById(imageIdToDelete);
	}

	@Test
	public void testUpdateImage() {
		long imageIdToUpdate = 1L;
		Image existingImage = new Image(new Product("Product 1", 10.99), "https://picsum.photos/200");
		Image updatedImageData = new Image(new Product("Product 2", 12.99), "https://picsum.photos/id/237/200/300");
		when(imageRepository.findById(imageIdToUpdate)).thenReturn(Optional.of(existingImage));
		when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Image updatedImage = imageService.updateImage(imageIdToUpdate, updatedImageData);

		assertNotNull(updatedImage);
		assertEquals(updatedImageData.getProduct(), updatedImage.getProduct());
		assertEquals(updatedImageData.getUrl(), updatedImage.getUrl());
	}

	@Test
	public void testGetAllImages() {
		List<Image> imageList = Arrays.asList(
				new Image(new Product("Product 1", 10.99), "https://picsum.photos/200"),
				new Image(new Product("Product 2", 12.99), "https://picsum.photos/id/237/200/300")
		);
		when(imageRepository.findAll()).thenReturn(imageList);
		List<Image> allImages = imageService.getAllImages();

		assertNotNull(allImages);
		assertEquals(imageList.size(), allImages.size());
	}

	@Test
	public void testGetImageById() {
		long imageIdToFind = 1L;
		Image foundImage = new Image(new Product("Product 1", 10.99), "https://picsum.photos/200");
		when(imageRepository.findById(imageIdToFind)).thenReturn(Optional.of(foundImage));
		Optional<Image> imageById = imageService.getImageById(imageIdToFind);

		assertTrue(imageById.isPresent());
		assertEquals(foundImage, imageById.get());
	}
}
