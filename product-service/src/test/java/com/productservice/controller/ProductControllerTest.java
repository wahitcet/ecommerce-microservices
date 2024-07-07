package com.productservice.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.productservice.model.Product;
import com.productservice.service.ProductService;

public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	public ProductControllerTest() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Test Product 1");
		product1.setStock(10);

		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Test Product 2");
		product2.setStock(5);

		when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

		mockMvc.perform(get("/products")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Test Product 1"))
				.andExpect(jsonPath("$[1].name").value("Test Product 2"));
	}

	@Test
	public void testGetProductById() throws Exception {
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setStock(10);

		when(productService.getProductById(1L)).thenReturn(Optional.of(product));

		mockMvc.perform(get("/products/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Test Product"));
	}

	@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setStock(10);
		product.setPrice(99.99);
		product.setDiscount(10.0);

//		given(productService.addProduct(product)).willReturn(product);
//
//		mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\": \"Test Product\", \"stock\": 10, \"price\": 99.99, \"discount\": 10.0}"))
//				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Test Product"))
//				.andExpect(jsonPath("$.stock").value(10)).andExpect(jsonPath("$.price").value(99.99))
//				.andExpect(jsonPath("$.discount").value(10.0));
	}

	@Test
	public void testUpdateStock() throws Exception {
		Product product = new Product(1L, "Product1", 10); // Mevcut stok değeri 10
		given(productService.updateStock(1L, 5)).willReturn(new Product(1L, "Product1", 5));
		mockMvc.perform(put("/products/1/stock").param("stock", "5")).andExpect(status().isOk())
				.andExpect(jsonPath("$.stock").value(5));
	}

}
