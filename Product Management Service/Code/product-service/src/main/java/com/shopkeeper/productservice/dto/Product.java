package com.shopkeeper.productservice.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopkeeper.productservice.utils.JsonToMapConverter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
public class Product {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Getter @Setter private String name;

  @Getter @Setter private Float price;

  @Getter @Setter private String category;

  @Getter @Setter private String description;

  @Column(name = "specifications", columnDefinition = "json")
  @Convert(converter = JsonToMapConverter.class)
  @Getter
  @Setter
  private HashMap<String, Object> specifications = new HashMap<>();

  @Column(name = "created_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  @Getter
  @Setter
  private Date createdTimestamp;

  @Column(name = "updated_timestamp")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  @Getter
  @Setter
  private Date updatedTimestamp;

  @Getter
  @Setter
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductImages> productImages;
}

