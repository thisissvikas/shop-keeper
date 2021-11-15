package com.shopkeeper.productservice.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  public List<ProductImages> getImagesByProductId(int id) {
    return imageRepository.findByProductId(id).orElse(null);
  }

  public ProductImages createProductImage(int id, MultipartFile file) {
    ProductImages imageDetails = new ProductImages();
    Product productDetails = productRepository.findById(id).orElse(null);
    try {
      imageDetails.setImage(file.getBytes());
    } catch (IOException e) {
      LOGGER.error("Error storing image to database.", e);
      ;
    }
    imageDetails.setCreatedTimestamp(new Date());
    imageDetails.setUpdatedTimestamp(new Date());
    imageDetails.setProduct(productDetails);
    imageRepository.save(imageDetails);
    return imageDetails;
  }

  public void deleteImageById(int id, int imageId) {
    Product productDetails = productRepository.findById(id).orElse(null);
    List<ProductImages> list = productDetails.getProductImages();
    if (list != null && !list.isEmpty()) {
      for (ProductImages image : list) {
        if (image.getId() == imageId) {
          imageRepository.deleteById(imageId);
        }
      }
    }
  }

  public void deleteAllImages(int id) {
    imageRepository.deleteByProductId(id);
  }
}

