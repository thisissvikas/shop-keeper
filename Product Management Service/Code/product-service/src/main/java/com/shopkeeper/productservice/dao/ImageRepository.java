package com.shopkeeper.productservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.ProductImages;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<ProductImages, Integer> {

	  Optional<List<ProductImages>> findByProductId(Integer id);

	  void deleteByProductId(Integer id);
	}
