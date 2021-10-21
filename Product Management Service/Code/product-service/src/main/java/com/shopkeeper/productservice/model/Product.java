package com.shopkeeper.productservice.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Product {
	
	@Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Getter
    @Setter
	private String name;
	
	@Getter
    @Setter
	private float price;
	
	@Getter
    @Setter
	private String category;
	
	@Getter
    @Setter
	private String description;
	
	@Getter
    @Setter
	private Map<String, String> specifications;
	
	@Getter
    @Setter
	private Date createdTimestamp;
	
	@Getter
    @Setter
	private Date updatedTimestamp;
  
}
