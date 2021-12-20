package com.shopkeeper.productservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		for (int i = 0; i < 10; i++) {
			mockProductList.add(Mockito.mock(Product.class));
		}
		Mockito.when(mockProductRepository.findAll()).thenReturn(mockProductList);
		ResponseEntity<List<Product>> response = productService.getProducts(null);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testGetProductsByCategory() {
		List<Product> mockProductList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			mockProductList.add(Mockito.mock(Product.class));
		}
		Mockito.when(mockProductRepository.findByCategory("electronics")).thenReturn(mockProductList);
		ResponseEntity<List<Product>> response = productService.getProducts("electronics");
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testCreateProduct() {
		HashMap<String, Object> specificationsMap = new HashMap<>();
		specificationsMap.put("pages", 400);
		specificationsMap.put("binding", "spiral binding");
		specificationsMap.put("colour", "multi");
		specificationsMap.put("ruling type", "unruled");
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProduct.getName()).thenReturn("Classmate Notebook");
		Mockito.when(mockProduct.getPrice()).thenReturn(56.0f);
		Mockito.when(mockProduct.getCategory()).thenReturn("stationery");
		Mockito.when(mockProduct.getDescription()).thenReturn("Classmate notebook unruled 100 pages");
		Mockito.when(mockProduct.getCreatedTimestamp()).thenReturn(new Date());
		Mockito.when(mockProduct.getUpdatedTimestamp()).thenReturn(new Date());
		Mockito.when(mockProduct.getSpecifications()).thenReturn(specificationsMap);
		Mockito.when(mockProductRepository.save(mockProduct)).thenReturn(mockProduct);
		ResponseEntity<Product> response = productService.createProduct(mockProduct);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
		Assert.isTrue(response.getBody().getName().equals("Classmate Notebook"), "The name must be Classmate Notebook");
		Assert.isTrue(response.getBody().getPrice().equals(56.0f), "The price must be 56.0f");
		Assert.isTrue(response.getBody().getCategory().equals("stationery"), "The category must be stationery");
		Assert.isTrue(response.getBody().getDescription().equals("Classmate notebook unruled 100 pages"),
				"The description must be Classmate notebook unruled 100 pages");
		Assert.isTrue(response.getBody().getSpecifications().equals(specificationsMap), "specifications must be same");
	}

	@Test
	public void testUpdateProductById() {
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProductRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockProduct));
		HashMap<String, Object> specificationsMap = new HashMap<>();
		specificationsMap.put("pages", 400);
		specificationsMap.put("binding", "spiral binding");
		specificationsMap.put("colour", "multi");
		specificationsMap.put("ruling type", "unruled");
		Mockito.when(mockProduct.getName()).thenReturn("Classmate Notebook");
		Mockito.when(mockProduct.getPrice()).thenReturn(56.0f);
		Mockito.when(mockProduct.getCategory()).thenReturn("stationery");
		Mockito.when(mockProduct.getDescription()).thenReturn("Classmate notebook unruled 100 pages");
		Mockito.when(mockProduct.getCreatedTimestamp()).thenReturn(new Date());
		Mockito.when(mockProduct.getUpdatedTimestamp()).thenReturn(new Date());
		Mockito.when(mockProduct.getSpecifications()).thenReturn(specificationsMap);
		Mockito.when(mockProductRepository.save(mockProduct)).thenReturn(mockProduct);
		ResponseEntity<Product> response = productService.updateProductById(mockProduct, 10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
		Assert.isTrue(response.getBody().getName().equals("Classmate Notebook"), "The name must be Classmate Notebook");
		Assert.isTrue(response.getBody().getPrice().equals(56.0f), "The price must be 56.0f");
		Assert.isTrue(response.getBody().getCategory().equals("stationery"), "The category must be stationery");
		Assert.isTrue(response.getBody().getDescription().equals("Classmate notebook unruled 100 pages"),
				"The description must be Classmate notebook unruled 100 pages");
		Assert.isTrue(response.getBody().getSpecifications().equals(specificationsMap), "specifications must be same");
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
		Mockito.doNothing().when(mockProductRepository).deleteById(Mockito.anyInt());
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
