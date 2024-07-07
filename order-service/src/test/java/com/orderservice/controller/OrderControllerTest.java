//package com.orderservice.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//
//import com.orderservice.model.Order;
//import com.orderservice.model.OrderStatus;
//import com.orderservice.service.OrderService;
//
//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class OrderControllerTest {
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@MockBean
//	private OrderService orderService;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	public void testGetAllOrders() throws Exception {
//		Order order1 = new Order();
//		order1.setId(1L);
//		order1.setStatus(OrderStatus.PENDING);
//
//		Order order2 = new Order();
//		order2.setId(2L);
//		order2.setStatus(OrderStatus.COMPLETED);
//
//		when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));
//
//		ResponseEntity<String> response = restTemplate.getForEntity("/orders", String.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
//		assertThat(response.getBody()).contains("\"status\":\"pending\"");
//		assertThat(response.getBody()).contains("\"status\":\"completed\"");
//	}
//
//	@Test
//	public void testGetOrderById() throws Exception {
//		Order order = new Order();
//		order.setId(1L);
//		order.setStatus(OrderStatus.PENDING);
//
//		when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));
//
//		ResponseEntity<String> response = restTemplate.getForEntity("/orders/1", String.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
//		assertThat(response.getBody()).contains("\"status\":\"pending\"");
//	}
//
//	@Test
//	public void testCreateOrder() throws Exception {
//		Order order = new Order();
//		order.setId(1L);
//		order.setProductId(1L);
//		order.setQuantity(5);
//		order.setStatus(OrderStatus.PENDING);
//
//		when(orderService.createOrder(order)).thenReturn(order);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<>("{\"productId\": 1, \"quantity\": 5}", headers);
//
//		ResponseEntity<String> response = restTemplate.postForEntity("/orders", entity, String.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).contains("\"status\":\"pending\"");
//	}
//
//	@Test
//	public void testUpdateOrderStatus() throws Exception {
//		Order order = new Order();
//		order.setId(1L);
//		order.setStatus(OrderStatus.PENDING);
//
//		when(orderService.updateOrderStatus(1L, OrderStatus.COMPLETED)).thenReturn(order);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange("/orders/1/status?status=completed", HttpMethod.PUT,
//				entity, String.class);
//
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).contains("\"status\":\"completed\"");
//	}
//}
