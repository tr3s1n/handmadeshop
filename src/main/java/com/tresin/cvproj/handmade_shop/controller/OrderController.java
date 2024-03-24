package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.OrderApi;
import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements OrderApi {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
		Order createdOrder = orderService.createOrder(orderDTO.toOrder());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@Override
	public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
		Order updatedOrder = orderService.createOrder(orderDTO.toOrder());
		Order resultOrder = orderService.updateOrder(id, updatedOrder);
		return ResponseEntity.ok(resultOrder);
	}

	@Override
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Order>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@Override
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getOrderById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
	}

	@Override
	public ResponseEntity<Order> getOrderByPaymentId(@PathVariable Long paymentId) {
		return ResponseEntity.ok(orderService.getOrderByPaymentId(paymentId));
	}

	@Override
	public ResponseEntity<Integer> getOrderCountByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(orderService.getOrderCountByProductId(productId));
	}
}
