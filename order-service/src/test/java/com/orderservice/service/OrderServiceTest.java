//package com.orderservice.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.orderservice.client.ProductClient;
//import com.orderservice.model.Order;
//import com.orderservice.model.OrderStatus;
//import com.orderservice.repository.OrderRepository;
//
//public class OrderServiceTest {
//
//	@InjectMocks
//	private OrderService orderService;
//
//	@Mock
//	private OrderRepository orderRepository;
//
//	@Mock
//	private ProductClient productClient;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	public void testCreateOrder() {
//		Order order = new Order();
//		order.setId(1L);
//		order.setProductId(1L);
//		order.setQuantity(5);
//		order.setStatus(OrderStatus.PENDING);
//
//		when(orderRepository.save(order)).thenReturn(order);
//
//		Order createdOrder = orderService.createOrder(order);
//
//		verify(productClient, times(1)).updateStock(order.getProductId(), -order.getQuantity());
//		assertEquals("pending", createdOrder.getStatus());
//	}
//
//	@Test
//	public void testUpdateOrderStatus() {
//		Order order = new Order();
//		order.setId(1L);
//		order.setStatus(OrderStatus.PENDING);
//
//		when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
//		when(orderRepository.save(order)).thenReturn(order);
//
//		Order updatedOrder = orderService.updateOrderStatus(1L, OrderStatus.COMPLETED);
//
//		assertEquals("completed", updatedOrder.getStatus());
//	}
//
//	@Test
//	public void testUpdateOrderStatus_OrderNotFound() {
//		when(orderRepository.findById(1L)).thenReturn(Optional.empty());
//
//		assertThrows(RuntimeException.class, () -> {
//			orderService.updateOrderStatus(1L, OrderStatus.COMPLETED);
//		});
//	}
//}
