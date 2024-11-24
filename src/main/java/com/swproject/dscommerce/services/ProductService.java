package com.swproject.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swproject.dscommerce.dto.ProductDTO;
import com.swproject.dscommerce.entities.Product;
import com.swproject.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Product product = productRepository.findById(id).get();
    return new ProductDTO(product);
  }
}
