package com.shopkeeper.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.shopkeeper.productservice.dao.ProductRepository;
import com.shopkeeper.productservice.dto.Product;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

	@Autowired
	ProductService productService;
	@MockBean
	ProductRepository mockProductRepository;

	@Test
	public void testGetProductById() {
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProductRepository.findById(10)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> response = productService.getProductById(10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testGetProductByIdForNull() {
		Mockito.when(mockProductRepository.findById(10)).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productService.getProductById(10);
		Assert.isNull(response.getBody(), "response is null");
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
	}

	@Test
	public void testGetAllProducts() {
		List<Product> mockProductList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			mockProductList.add(Mockito.mock(Product.class));
		}
		Mockito.when(mockProductRepository.findAll()).thenReturn(mockProductList);
		ResponseEntity<List<Product>> response = productService.getProducts(null);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
	
	@Test
	public void testGetProductsByCategory() {
		List<Product> mockProductList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			mockProductList.add(Mockito.mock(Product.class));
		}
		Mockito.when(mockProductRepository.findByCategory("electronics")).thenReturn(mockProductList);
		ResponseEntity<List<Product>> response = productService.getProducts("electronics");
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
	
	@Test
	public void testCreateProduct() {
		Product mockProduct = Mockito.mock(Product.class);
//		Mockito.when(mockProduct.setName("Classmate Notebook")).thenReturn(mockProduct.getName());
		Mockito.when(mockProductRepository.save(mockProduct)).thenReturn(mockProduct);
		ResponseEntity<Product> response = productService.createProduct(mockProduct);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
	
	@Test
	public void testUpdateProductById() {
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProductRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockProduct));
		Mockito.when(mockProductRepository.save(mockProduct)).thenReturn(mockProduct);
		ResponseEntity<Product> response = productService.updateProductById(mockProduct, 10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
	
	@Test
	public void testUpdateProductByIdForNull() {
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProductRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		ResponseEntity<Product> response = productService.updateProductById(mockProduct, 10);
		Assert.isNull(response.getBody(), "is 4xx error");
	}
	
	@Test
	public void testDeleteProductById() {
		Mockito.when(mockProductRepository.existsById(Mockito.anyInt())).thenReturn(true);
		ResponseEntity<?> response = productService.deleteProductById(10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
	
	@Test
	public void testDeleteProductByIdForNull() {
		Mockito.when(mockProductRepository.existsById(Mockito.anyInt())).thenReturn(false);
		ResponseEntity<?> response = productService.deleteProductById(10);
		Assert.isNull(response.getBody(), "is 4xx error");
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
	}
	
	@Test
	public void testDeleteAllProducts() {
		Mockito.doNothing().when(mockProductRepository).deleteAll();
		ResponseEntity<?> response = productService.deleteAllProducts();
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}
}
