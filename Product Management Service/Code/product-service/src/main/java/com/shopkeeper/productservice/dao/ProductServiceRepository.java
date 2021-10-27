package com.shopkeeper.productservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shopkeeper.productservice.dto.Product;

public interface ProductServiceRepository extends CrudRepository<Product, Integer>{
	
	List<Product> findByCategory(String category);
	
}
