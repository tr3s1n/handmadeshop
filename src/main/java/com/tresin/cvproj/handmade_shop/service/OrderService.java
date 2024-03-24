package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.OrderNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order createOrder(Order newOrder) {
        Order createdOrder = orderRepository.save(newOrder);
        logger.info("Order with ID {} created successfully", createdOrder.getId());
        return createdOrder;
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setUser(updatedOrder.getUser());
            existingOrder.setProducts(updatedOrder.getProducts());
            existingOrder.setPayment(updatedOrder.getPayment());

            Order savedOrder = orderRepository.save(existingOrder);
            logger.info("Order with ID {} updated successfully", orderId);

            return savedOrder;
        } else {
            logger.warn("Order with ID {} not found in method updateOrder", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    public void deleteOrder(Long orderId) {
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        if (existingOrderOptional.isPresent()) {
            orderRepository.deleteById(orderId);
            logger.info("Order with ID {} deleted successfully", orderId);
        } else {
            logger.warn("Order with ID {} not found in method deleteOrder", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            logger.info("Order with ID {} found", orderId);
        } else {
            logger.warn("Order with ID {} not found in method getOrderById", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
        return orderOptional;
    }

    // TODO: implement methods
	public List<Order> getOrdersByUserId(Long userId) {
        return null;
	}

    public Order getOrderByPaymentId(Long paymentId) {
        return null;
    }

    public Integer getOrderCountByProductId(Long productId) {
        return null;
    }
}
