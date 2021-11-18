package com.shopkeeper.productservice.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopkeeper.productservice.dto.ProductImages;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<ProductImages, Integer> {

  List<ProductImages> findByProductId(Integer productId);

  void deleteByProductId(Integer productId);
}
