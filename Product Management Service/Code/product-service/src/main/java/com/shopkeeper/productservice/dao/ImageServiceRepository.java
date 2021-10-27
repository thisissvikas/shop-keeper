package com.shopkeeper.productservice.dao;

import org.springframework.data.repository.CrudRepository;

import com.shopkeeper.productservice.dto.Product;

public interface ImageServiceRepository extends CrudRepository<Product, Integer>{
	
}
