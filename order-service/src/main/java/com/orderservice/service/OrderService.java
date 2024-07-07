package com.orderservice.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderservice.client.ProductClient;
import com.orderservice.model.Order;
import com.orderservice.model.OrderStatus;
import com.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductClient productClient;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Transactional
	public Order createOrder(Order order) {
		productClient.updateStock(order.getProductId(), -order.getQuantity());
		order.setStatus(OrderStatus.PENDING);
		return orderRepository.save(order);
	}

	@Transactional
	public Order updateOrderStatus(Long id, OrderStatus status) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (orderOptional.isPresent()) {
			Order order = orderOptional.get();
			order.setStatus(status);
			return orderRepository.save(order);
		}
		throw new RuntimeException("Order not found");
	}

	@Async
	public CompletableFuture<Void> processOrderAsync(Order order) {
		productClient.updateStock(order.getProductId(), -order.getQuantity());
		order.setStatus(OrderStatus.PENDING);
		orderRepository.save(order);
		return CompletableFuture.completedFuture(null);
	}
}
