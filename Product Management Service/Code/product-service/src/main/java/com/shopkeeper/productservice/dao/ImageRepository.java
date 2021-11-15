package com.shopkeeper.productservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.ProductImages;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<ProductImages, Integer> {

  void deleteByProductId(Integer id);
}
