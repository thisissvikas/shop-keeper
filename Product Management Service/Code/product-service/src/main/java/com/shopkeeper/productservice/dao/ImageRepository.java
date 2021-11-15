package com.shopkeeper.productservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.Product;

@Repository
public interface ImageRepository extends CrudRepository<Product, Integer>{
	
}
