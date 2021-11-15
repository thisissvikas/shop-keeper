package com.shopkeeper.productservice.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	
	List<Product> findByCategory(String category);
	
}
