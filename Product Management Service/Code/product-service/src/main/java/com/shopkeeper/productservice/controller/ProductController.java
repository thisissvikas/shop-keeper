package com.shopkeeper.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopkeeper.productservice.dto.Product;
import com.shopkeeper.productservice.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  @Autowired ProductService productSrvice;

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
    return new ResponseEntity<>(productSrvice.getProductById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String category) {
    if (category != null && !category.isEmpty()) {
      return new ResponseEntity<>(productSrvice.getProductByCategory(category), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(productSrvice.getAllProducts(), HttpStatus.OK);
    }
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product productDetails = productSrvice.createProduct(product);
    return new ResponseEntity<>(productDetails, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProductById(
      @RequestBody Product product, @PathVariable Integer id) {
    Product productData = productSrvice.updateProductById(product, id);
    return new ResponseEntity<>(productData, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductById(Integer id) {
    productSrvice.deleteProductById(id);
    return new ResponseEntity<>("Deleted", HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllProducts() {
    productSrvice.deleteAllProducts();
    return new ResponseEntity<>("Deleted", HttpStatus.OK);
  }
}
