package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	@NotBlank(message = "User is required")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@NotEmpty(message = "At least one product is required")
	@ManyToMany
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	@OneToOne(mappedBy = "order")
	private Payment payment;

	public Order(User user, List<Product> products) {
		this.user = user;
		this.products = products;
	}

	public Order(User user, List<Product> products, Payment payment) {
		this.user = user;
		this.products = products;
		this.payment = payment;
	}

	/**
	 * Updates the order entity based on the provided DTO.
	 *
	 * @param orderDTO The DTO containing the updated order information.
	 */
	public void updateFromDTO(OrderDTO orderDTO) {
		this.setUser(orderDTO.getUser());
		this.setProducts(orderDTO.getProducts());
		this.setPayment(orderDTO.getPayment());
	}

	/**
	 * Converts the order entity to a DTO.
	 *
	 * @return The DTO representation of the order entity.
	 */
	public OrderDTO toDTO() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setUser(this.user);
		orderDTO.setProducts(this.products);
		orderDTO.setPayment(this.payment);
		return orderDTO;
	}
}
