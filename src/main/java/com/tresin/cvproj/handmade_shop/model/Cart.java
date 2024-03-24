package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.CartDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@NotBlank(message = "User is required")
	private User user;

	@OneToMany(mappedBy = "cart")
	private List<Product> products;

	public Cart(User user, List<Product> products) {
		this.user = user;
		this.products = products;
	}

	/**
	 * Updates the cart entity based on the provided DTO.
	 *
	 * @param cartDTO The DTO containing the updated cart information.
	 */
	public void updateFromDTO(CartDTO cartDTO) {
		this.setUser(cartDTO.getUser());
		this.setProducts(cartDTO.getProducts());
	}

	/**
	 * Converts the cart entity to a DTO.
	 *
	 * @return The DTO representation of the cart entity.
	 */
	public CartDTO toDTO() {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setUser(this.getUser());
		cartDTO.setProducts(this.getProducts() != null ? this.getProducts() : Collections.emptyList());
		return cartDTO;
	}
}
