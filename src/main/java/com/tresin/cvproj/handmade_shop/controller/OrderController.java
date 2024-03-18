package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.OrderApi;
import com.tresin.cvproj.handmade_shop.dto.OrderDTO;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
		Order newOrder = new Order();
		newOrder.setUser(orderDTO.getUser());
		newOrder.setProducts(orderDTO.getProducts());
		Order createdOrder = orderService.createOrder(newOrder);

		return ResponseEntity.ok(createdOrder);
	}

	@Override
	public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
		Order updatedOrder = new Order();
		updatedOrder.setUser(orderDTO.getUser());
		updatedOrder.setProducts(orderDTO.getProducts());
		Order resultOrder = orderService.updateOrder(id, updatedOrder);

		if (resultOrder != null) {
			return ResponseEntity.ok(resultOrder);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();

		return ResponseEntity.ok(orders);
	}

	@Override
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<User> getUserByOrderId(@PathVariable Long id) {
		Order order = orderService.getOrderById(id).orElse(null);

		if (order != null) {
			User user = order.getUser();
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
