package com.shopkeeper.productservice.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.Product;

@Repository
public interface ProductServiceRepository extends PagingAndSortingRepository<Product, Integer>{
	
	List<Product> findByCategory(String category);
	
}
