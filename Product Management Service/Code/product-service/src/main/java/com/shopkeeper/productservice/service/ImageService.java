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

  public ResponseEntity<List<ProductImages>> getImagesByProductId(Integer id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product != null
        && product.getProductImages() != null
        && !product.getProductImages().isEmpty()) {
      return new ResponseEntity<>(product.getProductImages(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<ProductImages> createProductImage(Integer id, MultipartFile file) {
    ProductImages imageDetails = new ProductImages();
    Product productDetails = productRepository.findById(id).orElse(null);
    if (productDetails != null) {
      try {
        imageDetails.setImage(file.getBytes());
      } catch (IOException e) {
        LOGGER.error("Error storing image to database.", e);
      }
      imageDetails.setCreatedTimestamp(new Date());
      imageDetails.setUpdatedTimestamp(new Date());
      imageDetails.setProduct(productDetails);
      imageRepository.save(imageDetails);
      return new ResponseEntity<>(imageDetails, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<?> deleteImageById(Integer id, Integer imageId) {
    Product productDetails = productRepository.findById(id).orElse(null);
    Boolean found = false;
    if (productDetails != null
        && productDetails.getProductImages() != null
        && !productDetails.getProductImages().isEmpty()) {
      for (ProductImages image : productDetails.getProductImages()) {
        if (image.getId() == imageId) {
          found = true;
          imageRepository.delete(image);
          break;
        }
      }
      if (found == true) {
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<?> deleteAllImages(Integer id) {
    if (productRepository.existsById(id)) {
      imageRepository.deleteByProductId(id);
      return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
