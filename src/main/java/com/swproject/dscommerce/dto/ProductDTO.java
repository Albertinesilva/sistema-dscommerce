package com.swproject.dscommerce.dto;

import com.swproject.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

  private Long id;

  @Size(min = 3, max = 80, message = "The name must be between 3 and 80 characters long.")
  @NotBlank(message = "Mandatory field")
  private String name;

  @Size(min = 10, message = "The description must be at least 10 characters long.")
  @NotBlank(message = "Mandatory field")
  private String description;

  @Positive(message = "Price must be a positive value")
  private Double price;
  
  private String imgUrl;

  public ProductDTO() {
  }

  public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductDTO(Product entity) {
    id = entity.getId();
    name = entity.getName();
    description = entity.getDescription();
    price = entity.getPrice();
    imgUrl = entity.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

}
