package com.shopkeeper.productservice.dao;

import org.springframework.data.repository.CrudRepository;

import com.shopkeeper.productservice.model.Product;

public interface ProductServiceRepository extends CrudRepository<Product, Integer>{
	
}
