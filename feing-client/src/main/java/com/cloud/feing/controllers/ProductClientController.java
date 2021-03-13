package com.cloud.feing.controllers;

import com.cloud.client.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("client")
public interface ProductClientController {

    @GetMapping("/products")
    List<Product> getAllProducts();
}
