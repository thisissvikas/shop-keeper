package com.shopkeeper.productservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Getter
  @Setter
  @Lob
  @Column(name = "image", columnDefinition = "BLOB")
  private byte[] image;

  @Getter
  @Setter
  @Column(name = "created_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  private Date createdTimestamp;

  @Getter
  @Setter
  @Column(name = "updated_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  private Date updatedTimestamp;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
}
