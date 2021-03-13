package com.cloud.client.services;

import com.cloud.client.models.Product;
import com.cloud.client.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }
}
