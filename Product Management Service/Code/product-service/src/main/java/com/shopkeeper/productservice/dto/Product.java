package com.shopkeeper.productservice.dto;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
public class Product {

  @Getter
  @Setter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Getter @Setter private String name;

  @Getter @Setter private Float price;

  @Getter @Setter private String category;

  @Getter @Setter private String description;

  @Getter @Setter private HashMap<String, String> specifications;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  @Getter @Setter private Date createdTimestamp;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  @Getter @Setter private Date updatedTimestamp;
}
