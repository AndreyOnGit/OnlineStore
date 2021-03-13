package com.cloud.feing.controllers;

import com.cloud.client.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ProductClientController productClientController;

//    http://localhost:8190/products.html

    @GetMapping
    public List<Product> showAllProducts() {
        return productClientController.getAllProducts();
    }
}
