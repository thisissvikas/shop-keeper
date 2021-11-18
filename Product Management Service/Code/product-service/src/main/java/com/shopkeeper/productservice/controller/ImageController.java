package com.shopkeeper.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopkeeper.productservice.dto.ProductImages;
import com.shopkeeper.productservice.service.ImageService;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

  @Autowired ImageService imageService;

  @GetMapping
  public ResponseEntity<List<ProductImages>> getImagesByProductId(@RequestParam int productId) {
    return imageService.getImagesByProductId(productId);
  }

  @PostMapping
  public ResponseEntity<ProductImages> createImage(
      @RequestParam int productId, @RequestParam("file") MultipartFile file) {
    return imageService.createProductImage(productId, file);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteAllImagesByProductId(@RequestParam int productId) {
    return imageService.deleteAllImagesByProductId(productId);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteImageById(@PathVariable("id") int imageId) {
    return imageService.deleteImageById(imageId);
  }
}
