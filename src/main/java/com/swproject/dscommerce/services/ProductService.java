package com.swproject.dscommerce.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.swproject.dscommerce.dto.ProductDTO;
import com.swproject.dscommerce.entities.Product;
import com.swproject.dscommerce.repositories.ProductRepository;
import com.swproject.dscommerce.services.exception.DataBaseIntegrityViolationException;
import com.swproject.dscommerce.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

  private static final Logger log = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new ProductDTO(product);
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> products = productRepository.findAll(pageable);
    return products.map(x -> new ProductDTO(x));
  }

  @Transactional(readOnly = false)
  public ProductDTO insert(ProductDTO dto) {
    Product product = new Product();
    copyDtoToEntity(dto, product);
    product = productRepository.save(product);
    return new ProductDTO(product);

    // Product product = new Product();
    // BeanUtils.copyProperties(dto, product);
    // productRepository.save(product);
    // return new ProductDTO(product);
  }

  @Transactional(readOnly = false)
  public ProductDTO update(long id, ProductDTO dto) {
    try {
      Product product = productRepository.getReferenceById(id);
      copyDtoToEntity(dto, product);
      product = productRepository.save(product);
      return new ProductDTO(product);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Recurso não encontrado: " + id);
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
  public void delete(Long id) {

    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product not found: " + id);
    }

    try {
      productRepository.deleteById(id);

    } catch (DataIntegrityViolationException e) {
      throw new DataBaseIntegrityViolationException(
          "Cannot delete product with id " + id + " due to existing relationships.");
    }
  }

  private void copyDtoToEntity(ProductDTO dto, Product product) {
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setImgUrl(dto.getImgUrl());
  }
}
