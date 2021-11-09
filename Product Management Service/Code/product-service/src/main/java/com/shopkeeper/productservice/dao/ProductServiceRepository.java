package com.shopkeeper.productservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.Product;

@Repository
public interface ProductServiceRepository extends CrudRepository<Product, Integer>{
	
	List<Product> findByCategory(Optional<String> category);
	
}
