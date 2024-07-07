package com.productservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	public ProductServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetProductById() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setStock(10);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Optional<Product> result = productService.getProductById(1L);
		assertEquals("Test Product", result.get().getName());
		assertEquals(10, result.get().getStock());
	}

	@Test
	public void testUpdateStock() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Test Product");
		product.setStock(10);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);

		Product result = productService.updateStock(1L, 5);
		assertEquals(5, result.getStock());
	}
}
