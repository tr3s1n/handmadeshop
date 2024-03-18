package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.CartApi;
import com.tresin.cvproj.handmade_shop.dto.CartDTO;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController implements CartApi {

	private final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@Override
	public ResponseEntity<Cart> createCart(@Valid @RequestBody CartDTO cartDTO) {
		Cart newCart = new Cart();
		newCart.setUser(cartDTO.getUser());
		newCart.setProducts(cartDTO.getProducts());
		Cart createdCart = cartService.createCart(newCart);

		return ResponseEntity.ok(createdCart);
	}

	@Override
	public ResponseEntity<Cart> updateCart(@PathVariable Long id, @Valid @RequestBody CartDTO cartDTO) {
		Cart updatedCart = new Cart();
		updatedCart.setUser(cartDTO.getUser());
		updatedCart.setProducts(cartDTO.getProducts());
		Cart resultCart = cartService.updateCart(id, updatedCart);

		if (resultCart != null) {
			return ResponseEntity.ok(resultCart);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Cart>> getAllCarts() {
		List<Cart> carts = cartService.getAllCarts();

		return ResponseEntity.ok(carts);
	}

	@Override
	public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
		return cartService.getCartById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<User> getUserByCartId(@PathVariable Long id) {
		Cart cart = cartService.getCartById(id).orElse(null);

		if (cart != null) {
			User user = cart.getUser();
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
