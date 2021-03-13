package com.cloud.feing.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ProductClientController productClientController;

//    http://localhost:8190/products

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productClientController.getAllProducts());
        return "products";
    }
}
