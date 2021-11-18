package com.shopkeeper.productservice.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopkeeper.productservice.dao.ImageRepository;
import com.shopkeeper.productservice.dao.ProductRepository;
import com.shopkeeper.productservice.dto.Product;
import com.shopkeeper.productservice.dto.ProductImages;

@Service
@Transactional
public class ImageService {

  @Autowired ImageRepository imageRepository;

  @Autowired ProductRepository productRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

  public ResponseEntity<List<ProductImages>> getImagesByProductId(int productId) {
    List<ProductImages> imageDetails = imageRepository.findByProductId(productId);
    if (imageDetails != null && !imageDetails.isEmpty()) {
      return new ResponseEntity<>(imageDetails, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<ProductImages> createProductImage(int productId, MultipartFile file) {
    ProductImages imageDetails = new ProductImages();
    Product productDetails = productRepository.findById(productId).orElse(null);
    if (productDetails != null) {
      try {
        imageDetails.setImage(file.getBytes());
        imageDetails.setCreatedTimestamp(new Date());
        imageDetails.setUpdatedTimestamp(new Date());
        imageDetails.setProduct(productDetails);
        imageRepository.save(imageDetails);
        return new ResponseEntity<>(imageDetails, HttpStatus.CREATED);
      } catch (IOException e) {
        LOGGER.error("Error storing image to database.", e);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }

    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<?> deleteImageById(int imageId) {
    if (imageRepository.existsById(imageId)) {
      imageRepository.deleteById(imageId);
      return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<?> deleteAllImagesByProductId(int productId) {
    if (productRepository.existsById(productId)) {
      imageRepository.deleteByProductId(productId);
      return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
