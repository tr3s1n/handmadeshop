package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order newOrder = new Order();
        newOrder.setUser(orderDTO.getUser());
        newOrder.setProducts(orderDTO.getProducts());
        Order createdOrder = orderService.createOrder(newOrder);

        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = new Order();
        updatedOrder.setUser(orderDTO.getUser());
        updatedOrder.setProducts(orderDTO.getProducts());
        Order resultOrder = orderService.updateOrder(orderId, updatedOrder);

        if (resultOrder != null) {
            return ResponseEntity.ok(resultOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
