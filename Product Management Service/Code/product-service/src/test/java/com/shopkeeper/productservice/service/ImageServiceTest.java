package com.shopkeeper.productservice.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Assert;

import com.shopkeeper.productservice.dao.ImageRepository;
import com.shopkeeper.productservice.dao.ProductRepository;
import com.shopkeeper.productservice.dto.Product;
import com.shopkeeper.productservice.dto.ProductImages;

@SpringBootTest(classes = ImageService.class)
public class ImageServiceTest {

	@Autowired
	private ImageService imageService;

	@MockBean
	private ImageRepository mockImageRepository;

	@MockBean
	private ProductRepository mockProductRepository;

	@Test
	public void testGetImagesByProductId() {
		List<ProductImages> mockImagesList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			mockImagesList.add(Mockito.mock(ProductImages.class));
		}
		Mockito.when(mockImageRepository.findByProductId(Mockito.anyInt())).thenReturn(mockImagesList);
		ResponseEntity<List<ProductImages>> response = imageService.getImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testGetImagesByProductIdForEmpty() {
		List<ProductImages> mockImagesList = new ArrayList<>();
		Mockito.when(mockImageRepository.findByProductId(Mockito.anyInt())).thenReturn(mockImagesList);
		ResponseEntity<List<ProductImages>> response = imageService.getImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
	}

	@Test
	public void testGetImagesByProductIdForNull() {
		Mockito.when(mockImageRepository.findByProductId(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<List<ProductImages>> response = imageService.getImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
	}

	@Test
	public void testCreateProductImage() {
		ProductImages mockImage = Mockito.mock(ProductImages.class);
		Product mockProduct = Mockito.mock(Product.class);
		Mockito.when(mockProductRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockProduct));
		FileInputStream inputFile;
		MockMultipartFile file = null;
		try {
			inputFile = new FileInputStream("C:\\Temp\\classmate_.jpg");
			file = new MockMultipartFile("classmate notebook", "classmate_", "multipart/form-data", inputFile);
			Mockito.when(mockImage.getImage()).thenReturn(file.getBytes());
			Mockito.when(mockImage.getCreatedTimestamp()).thenReturn(new Date());
			Mockito.when(mockImage.getUpdatedTimestamp()).thenReturn(new Date());
			Mockito.when(mockImageRepository.save(mockImage)).thenReturn(mockImage);
			ResponseEntity<ProductImages> response = imageService.createProductImage(10, file);
			Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
			Assert.isTrue(response.getBody().getImage().equals(file.getBytes()), "the image must be present");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateProductImageForNull() {
		Mockito.when(mockProductRepository.findById(10)).thenReturn(Optional.empty());
		FileInputStream inputFile;
		MockMultipartFile file = null;
		try {
			inputFile = new FileInputStream("C:\\Temp\\classmate_.jpg");
			file = new MockMultipartFile("classmate notebook", "classmate_", "multipart/form-data", inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ResponseEntity<ProductImages> response = imageService.createProductImage(10, file);
		Assert.isNull(response.getBody(), "response is null");
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
		Assert.isNull(response.getBody(), "response is null");
	}

	@Test
	public void testCreateProductImageForBadRequest() {
		
	}

	@Test // This test is failing
	public void testDeleteImageByIdForTrue() {
		Mockito.when(mockImageRepository.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.doNothing().when(mockImageRepository).deleteById(Mockito.anyInt());
		ResponseEntity<?> response = imageService.getImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testDeleteImageByIdForFalse() {
		Mockito.when(mockImageRepository.existsById(Mockito.anyInt())).thenReturn(false);
		ResponseEntity<?> response = imageService.getImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
		Assert.isNull(response.getBody(), "response is null");
	}

	@Test
	public void testDeleteAllImagesByProductIdForTrue() {
		Mockito.when(mockProductRepository.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.doNothing().when(mockImageRepository).deleteByProductId(10);
		ResponseEntity<?> response = imageService.deleteAllImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "is 2xx successful");
	}

	@Test
	public void testDeleteAllImagesByProductIdForFalse() {
		Mockito.when(mockProductRepository.existsById(Mockito.anyInt())).thenReturn(false);
		ResponseEntity<?> response = imageService.deleteAllImagesByProductId(10);
		Assert.isTrue(response.getStatusCode().is4xxClientError(), "is 4xx error");
		Assert.isNull(response.getBody(), "response is null");
	}
}
