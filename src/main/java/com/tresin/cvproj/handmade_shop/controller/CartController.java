package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.CartDTO;
import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@Valid @RequestBody CartDTO cartDTO) {
        Cart newCart = new Cart();
        newCart.setUser(cartDTO.getUser());
        newCart.setProducts(cartDTO.getProducts());
        Cart createdCart = cartService.createCart(newCart);

        return ResponseEntity.ok(createdCart);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @Valid @RequestBody CartDTO cartDTO) {
        Cart updatedCart = new Cart();
        updatedCart.setUser(cartDTO.getUser());
        updatedCart.setProducts(cartDTO.getProducts());
        Cart resultCart = cartService.updateCart(cartId, updatedCart);

        if (resultCart != null) {
            return ResponseEntity.ok(resultCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);

        return ResponseEntity.noContent().build();
    }

    // Again, not sure if this is good to have for any purpose
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();

        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
