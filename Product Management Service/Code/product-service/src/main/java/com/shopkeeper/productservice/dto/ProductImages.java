package com.shopkeeper.productservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "product_images")
public class ProductImages {

  @Getter @Setter @Id  @GeneratedValue(strategy = GenerationType.AUTO) 
  @Column(name = "id")
  private Integer id;

  @Getter @Setter @Lob private byte[] image;

  @Getter @Setter @Column(name = "image_description")
  private String imageDescription;
  
  @Getter @Setter @Column(name = "created_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  private Date createdTimestamp;

  @Getter @Setter @Column(name = "updated_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  private Date updatedTimestamp;
  
  @Getter @Setter @ManyToOne
  @JoinColumn(name="product_id", nullable=false)
  private Product product;

}
