package com.swproject.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swproject.dscommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
