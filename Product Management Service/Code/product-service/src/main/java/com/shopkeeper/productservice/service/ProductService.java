package com.shopkeeper.productservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopkeeper.productservice.dao.ProductRepository;
import com.shopkeeper.productservice.dto.Product;

@Service
public class ProductService {

  @Autowired ProductRepository productRepository;

  public ResponseEntity<List<Product>> getProducts(String category) {
    if (category != null && !category.isEmpty()) {
      return getProductByCategory(category);
    } else {
      return getAllProducts();
    }
  }

  private ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = new ArrayList<Product>();
    productRepository.findAll().forEach(products::add);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  public ResponseEntity<Product> getProductById(Integer id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product != null) {
      return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  private ResponseEntity<List<Product>> getProductByCategory(String category) {
    return new ResponseEntity<>(productRepository.findByCategory(category), HttpStatus.OK);
  }

  public ResponseEntity<Product> createProduct(Product product) {
    Product productData = new Product();
    productData.setName(product.getName());
    productData.setPrice(product.getPrice());
    productData.setDescription(product.getDescription());
    productData.setCategory(product.getCategory());
    productData.setSpecifications(product.getSpecifications());
    productData.setCreatedTimestamp(new Date());
    productData.setUpdatedTimestamp(new Date());
    productRepository.save(productData);
    return new ResponseEntity<>(productData, HttpStatus.CREATED);
  }

  public ResponseEntity<Product> updateProductById(Product product, Integer id) {
    Product productData = productRepository.findById(id).orElse(null);
    if (productData != null) {
      productData.setId(productData.getId());
      productData.setName(product.getName());
      productData.setPrice(product.getPrice());
      productData.setDescription(product.getDescription());
      productData.setCategory(product.getCategory());
      productData.setSpecifications(product.getSpecifications());
      productData.setCreatedTimestamp(productData.getCreatedTimestamp());
      productData.setUpdatedTimestamp(new Date());
      productRepository.save(productData);
      return new ResponseEntity<>(productData, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<?> deleteProductById(Integer id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<?> deleteAllProducts() {
    productRepository.deleteAll();
    return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
  }
}
