package com.swproject.dscommerce.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swproject.dscommerce.dto.ProductDTO;
import com.swproject.dscommerce.entities.Product;
import com.swproject.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

  private static final Logger log = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Product product = productRepository.findById(id).get();
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
    Product product = productRepository.getReferenceById(id);
    copyDtoToEntity(dto, product);
    product = productRepository.save(product);
    return new ProductDTO(product);
  }

  @Transactional(readOnly = false)
  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  private void copyDtoToEntity(ProductDTO dto, Product product) {
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setImgUrl(dto.getImgUrl());
  }
}
