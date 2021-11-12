package com.shopkeeper.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  @GetMapping("/{id}")
  public ResponseEntity<List<ProductImages>> getImagesByProductId(@PathVariable Integer id) {
    return new ResponseEntity<>(imageService.getImagesByProductId(id), HttpStatus.OK);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity<ProductImages> createImage(
      @PathVariable Integer id, @RequestParam("file") MultipartFile file) {
    return new ResponseEntity<>(imageService.createProductImage(id, file), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAllImagesByProductId(@PathVariable Integer id) {
    imageService.deleteAllImages(id);
    return new ResponseEntity<>("Deleted", HttpStatus.OK);
  }

  @DeleteMapping("/{id}/{imageId}")
  public ResponseEntity<?> deleteAllProducts(
      @PathVariable Integer id, @PathVariable Integer imageId) {
    imageService.deleteImageById(id, imageId);
    return new ResponseEntity<>("Deleted", HttpStatus.OK);
  }
}
