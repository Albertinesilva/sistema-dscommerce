package com.swproject.dscommerce.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swproject.dscommerce.entities.Product;
import com.swproject.dscommerce.repositories.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public String test() {
    Optional<Product> result = productRepository.findById(1L);
    return result.get().getName();
  }
}
