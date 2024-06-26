package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.CartApi;
import com.tresin.cvproj.handmade_shop.dto.CartDTO;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		Cart createdCart = cartService.createCart(cartDTO.toCart());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
	}

	@Override
	public ResponseEntity<Cart> updateCart(@PathVariable Long id, @Valid @RequestBody CartDTO cartDTO) {
		Cart updatedCart = cartService.createCart(cartDTO.toCart());
		Cart resultCart = cartService.updateCart(id, updatedCart);
		return ResponseEntity.ok(resultCart);
	}

	@Override
	public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Cart>> getAllCarts() {
		return ResponseEntity.ok(cartService.getAllCarts());
	}

	@Override
	public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
		return ResponseEntity.ok(cartService.getCartById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(cartService.getCartByUserId(userId).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Product>> getCartProducts(@PathVariable Long id) {
		return ResponseEntity.ok(cartService.getCartProducts(id));
	}
}
