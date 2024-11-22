package com.swproject.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swproject.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
