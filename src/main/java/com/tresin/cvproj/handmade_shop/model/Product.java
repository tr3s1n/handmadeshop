package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Price is required")
	private double price;
	@ManyToOne
	private Cart cart;
	@OneToMany(mappedBy = "product")
	private List<Image> images;
	@ManyToMany(mappedBy = "products")
	private List<Order> orders;
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;
	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(String name, double price, List<Category> categories) {
		this.name = name;
		this.price = price;
		this.categories = categories;
	}

	public Product(String name, double price, Cart cart, List<Image> images, List<Order> orders, List<Review> reviews, List<Category> categories) {
		this.name = name;
		this.price = price;
		this.cart = cart;
		this.images = images;
		this.orders = orders;
		this.reviews = reviews;
		this.categories = categories;
	}

	/**
	 * Updates the product entity based on the provided DTO.
	 *
	 * @param productDTO The DTO containing the updated product information.
	 */
	public void updateFromDTO(ProductDTO productDTO) {
		this.setName(productDTO.getName());
		this.setPrice(productDTO.getPrice());
		this.setCategories(productDTO.getCategories());
		this.setImages(productDTO.getImages());
	}

	/**
	 * Converts the product entity to a DTO.
	 *
	 * @return The DTO representation of the product entity.
	 */
	public ProductDTO toDTO() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(this.name);
		productDTO.setPrice(this.price);
		productDTO.setImages(this.images);
		productDTO.setCategories(this.getCategories());
		return productDTO;
	}
}
