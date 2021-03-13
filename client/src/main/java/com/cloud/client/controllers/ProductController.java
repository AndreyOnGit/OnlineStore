package com.cloud.client.controllers;

import com.cloud.client.models.Product;
import com.cloud.client.repositories.ProductRepository;
import com.cloud.client.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// http://localhost:19872/products/
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

//    {"name":"smth","price":100}
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
