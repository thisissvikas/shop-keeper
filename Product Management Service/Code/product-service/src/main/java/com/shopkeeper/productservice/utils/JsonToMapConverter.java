package com.shopkeeper.productservice.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);

  @Override
  @SuppressWarnings("unchecked")
  public Map<String, Object> convertToEntityAttribute(String attribute) {
    if (attribute == null) {
      return new HashMap<>();
    }
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(attribute, HashMap.class);
    } catch (IOException e) {
      LOGGER.error("Convert error while trying to convert string(JSON) to map data structure. ", e);
    }
    return new HashMap<>();
  }

  @Override
  public String convertToDatabaseColumn(Map<String, Object> dbData) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(dbData);
    } catch (JsonProcessingException e) {
      LOGGER.error("Could not convert map to json string. ", e);
      return null;
    }
  }
}
