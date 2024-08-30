package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.OrderNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.repository.OrderRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The OrderService class provides operations for managing orders in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving orders.
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Creates a new order.
     *
     * @param newOrder The order to be created.
     * @return The created order.
     * @throws IllegalArgumentException     If the newOrder is null.
     * @throws ConstraintViolationException If the newOrder violates constraints specified by annotations in the Order model class.
     */
    public Order createOrder(Order newOrder) {
        if (newOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        Order createdOrder = orderRepository.save(newOrder);
        logger.info("Order with ID {} created successfully", createdOrder.getId());
        return createdOrder;
    }

    /**
     * Updates an existing order.
     *
     * @param id           The ID of the order to update.
     * @param updatedOrder The updated order information.
     * @return The updated order.
     * @throws IllegalArgumentException     If id or updatedOrder is null or if id is invalid.
     * @throws OrderNotFoundException       If the order with the given ID is not found.
     * @throws ConstraintViolationException If the updatedOrder violates constraints specified by annotations in the Order model class.
     */
    public Order updateOrder(Long id, Order updatedOrder) {
        if (id == null || id <= 0 || updatedOrder == null) {
            throw new IllegalArgumentException("Invalid order or ID");
        }

        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.updateFromDTO(updatedOrder.toDTO());
                    Order savedOrder = orderRepository.save(existingOrder);
                    logger.info("Order with ID {} updated successfully", id);
                    return savedOrder;
                })
                .orElseThrow(() -> {
                    logger.error("Order with ID {} not found in method updateOrder", id);
                    return new OrderNotFoundException("Order with ID " + id + " not found");
                });
    }

    /**
     * Deletes an existing order.
     *
     * @param id The ID of the order to delete.
     * @throws IllegalArgumentException  If the ID is null or invalid.
     * @throws OrderNotFoundException     If the order with the given ID is not found.
     */
    public void deleteOrder(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Optional<Order> orderOptional = orderRepository.findById(id);
        orderOptional.ifPresentOrElse(
                order -> {
                    orderRepository.deleteById(id);
                    logger.info("Order with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Order with ID {} not found in method deleteOrder", id);
                    throw new OrderNotFoundException("Order with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all orders.
     *
     * @return A list containing all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves the order with the specified ID.
     *
     * @param id The ID of the order to retrieve.
     * @return An Optional containing the order with the given ID, if present.
     * @throws IllegalArgumentException If the ID is null or invalid.
     * @throws OrderNotFoundException    If the order with the given ID is not found.
     */
    public Optional<Order> getOrderById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Optional<Order> orderOptional = orderRepository.findById(id);
        orderOptional.ifPresentOrElse(
                order -> logger.info("Order with ID {} found", id),
                () -> {
                    logger.error("Order with ID {} not found in method getOrderById", id);
                    throw new OrderNotFoundException("Order with ID " + id + " not found");
                }
        );
        return orderOptional;
    }

    /**
     * Retrieves the list of orders associated with the specified user ID.
     *
     * @param userId The ID of the user.
     * @return A list of orders associated with the user.
     * @throws IllegalArgumentException If the user ID is null or invalid.
     */
    public List<Order> getOrdersByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        return orderRepository.findOrdersByUser_Id(userId);
    }

    /**
     * Retrieves the order associated with the specified payment ID.
     *
     * @param paymentId The ID of the payment.
     * @return The order associated with the payment.
     * @throws IllegalArgumentException If the payment ID is null or invalid.
     */
    public Optional<Order> getOrderByPaymentId(Long paymentId) {
        if (paymentId == null || paymentId <= 0) {
            throw new IllegalArgumentException("Invalid payment ID");
        }

        Optional<Order> orderOptional = orderRepository.findOrderByPaymentId(paymentId);
        orderOptional.ifPresentOrElse(
                order -> logger.info("Order for payment with ID {} found", paymentId),
                () -> {
                    logger.error("Order for payment with ID {} not found in method getOrderByPaymentId", paymentId);
                    throw new OrderNotFoundException("Order for payment with ID " + paymentId + " not found");
                }
        );

        return orderOptional;
    }

    /**
     * Retrieves the count of orders associated with the specified product ID.
     *
     * @param productId The ID of the product.
     * @return The count of orders associated with the product.
     * @throws IllegalArgumentException If the product ID is null or invalid.
     */
    public Integer getOrderCountByProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        return orderRepository.countOrdersByProductId(productId);
    }
}

