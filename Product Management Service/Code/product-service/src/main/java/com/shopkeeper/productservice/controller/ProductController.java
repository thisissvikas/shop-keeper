package com.shopkeeper.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    return productSrvice.getProductById(id);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(
      @RequestParam(required = false) String category) {
    return productSrvice.getProducts(category);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return productSrvice.createProduct(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProductById(
      @RequestBody Product product, @PathVariable Integer id) {
    return productSrvice.updateProductById(product, id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
    return productSrvice.deleteProductById(id);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllProducts() {
    return productSrvice.deleteAllProducts();
  }
}
