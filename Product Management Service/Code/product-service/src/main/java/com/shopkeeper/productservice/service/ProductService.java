package com.shopkeeper.productservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopkeeper.productservice.dao.ProductServiceRepository;
import com.shopkeeper.productservice.dto.Product;

@Service
public class ProductService {

  @Autowired ProductServiceRepository productRepository;

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<Product>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  public Product getProductById(Integer id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<Product> getProductByCategory(String category) {
    List<Product> products = productRepository.findByCategory(category);
    return products;
  }

  public Product createProduct(Product product) {
    Product productData = new Product();
    productData.setId(product.getId());
    productData.setName(product.getName());
    productData.setPrice(product.getPrice());
    productData.setDescription(product.getDescription());
    productData.setCategory(product.getCategory());
    productData.setSpecifications(product.getSpecifications());
    productData.setCreatedTimestamp(new Date());
    productData.setUpdatedTimestamp(new Date());
    productRepository.save(productData);
    return productData;
  }

  public Product updateProductById(Product product, Integer id) {
    Product productData = productRepository.findById(id).orElse(null);
    productData.setName(product.getName());
    productData.setPrice(product.getPrice());
    productData.setDescription(product.getDescription());
    productData.setCategory(product.getCategory());
    productData.setSpecifications(product.getSpecifications());
    productData.setCreatedTimestamp(productData.getCreatedTimestamp());
    productData.setUpdatedTimestamp(new Date());
    productRepository.save(productData);
    return productData;
  }

  public void deleteProductById(Integer id) {
    productRepository.deleteById(id);
  }

  public void deleteAllProducts() {
    productRepository.deleteAll();
  }
}
