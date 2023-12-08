package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.CartNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart createCart(Cart newCart) {
        Cart createdCart = cartRepository.save(newCart);
        logger.info("Cart with ID {} created successfully", createdCart.getId());
        return createdCart;
    }

    public Cart updateCart(Long cartId, Cart updatedCart) {
        Optional<Cart> existingCartOptional = cartRepository.findById(cartId);
        if (existingCartOptional.isPresent()) {
            Cart existingCart = existingCartOptional.get();
            existingCart.setUser(updatedCart.getUser()); // Not sure if it makes sense, it is OneToOne relationship so user will never change
            existingCart.setProducts(updatedCart.getProducts());

            Cart savedCart = cartRepository.save(existingCart);
            logger.info("Cart with ID {} updated successfully", cartId);

            return savedCart;
        } else {
            logger.warn("Cart with ID {} not found in method updateProduct", cartId);
            throw new CartNotFoundException("Cart with ID " + cartId + " not found");
        }
    }

    public void deleteCart(Long cartId) {
        Optional<Cart> existingCartOptional = cartRepository.findById(cartId);
        if (existingCartOptional.isPresent()) {
            cartRepository.deleteById(cartId);
            logger.info("Cart with ID {} deleted successfully", cartId);
        } else {
            logger.warn("Cart with ID {} not found in method deleteProduct", cartId);
            throw new CartNotFoundException("User with ID " + cartId + " not found");
        }
    }

    // Not sure if this is ever going to be needed
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            logger.info("Cart with ID {} found", cartId);
        } else {
            logger.warn("Cart with ID {} not found in method getCartById", cartId);
            throw new CartNotFoundException("Cart with ID " + cartId + " not found");
        }
        return cartOptional;
    }

}
