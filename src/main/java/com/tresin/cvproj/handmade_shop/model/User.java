package com.tresin.cvproj.handmade_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NotBlank(message = "Username is required")
	private String username;
	@JsonIgnore
	private String password;
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	@NotBlank(message = "First name is required")
	private String firstName;
	@NotBlank(message = "Last name is required")
	private String lastName;
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	@NotBlank(message = "Address is required")
	@OneToOne(mappedBy = "user")
	private Address address;
	@OneToOne(mappedBy = "user")
	private Cart cart;
	@NotBlank(message = "Role is required")
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

	// Builder for Constructor
	@Builder
	public User(String username, String password, String email, Address address, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.roles = (roles != null) ? roles : new HashSet<>();
	}

	/**
	 * Updates the user entity based on the provided DTO.
	 *
	 * @param userDTO The DTO containing the updated user information.
	 */
	public void updateFromDTO(UserDTO userDTO) {
		this.setUsername(userDTO.getUsername());
		this.setEmail(userDTO.getEmail());
		this.setFirstName(userDTO.getFirstName());
		this.setLastName(userDTO.getLastName());
		this.setAddress(userDTO.getAddress());
		this.setRoles(userDTO.getRoles());
		this.setOrders(userDTO.getOrders());
	}

	/**
	 * Converts the user entity to a DTO.
	 *
	 * @return The DTO representation of the user entity.
	 */
	public UserDTO toDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(this.username);
		userDTO.setEmail(this.email);
		userDTO.setFirstName(this.firstName);
		userDTO.setLastName(this.lastName);
		userDTO.setAddress(this.address);
		userDTO.setRoles(this.roles);
		userDTO.setOrders(this.orders);
		return userDTO;
	}

	public boolean isEmpty() {
		return username == null || username.isEmpty() ||
				email == null || email.isEmpty() ||
				password == null || password.isEmpty();
	}
}
