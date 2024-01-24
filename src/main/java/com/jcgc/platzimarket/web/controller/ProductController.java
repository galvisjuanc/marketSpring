package com.jcgc.platzimarket.web.controller;

import com.jcgc.platzimarket.domain.Product;
import com.jcgc.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public List<Product> getAll() {
        return productService.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productService.getProduct(productId);
    }

    public  Optional<List<Product>> getByCategory(int categoryId) {
        return productService.getByCategory(categoryId);
    }


}