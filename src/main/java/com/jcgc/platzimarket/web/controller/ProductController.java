package com.jcgc.platzimarket.web.controller;

import com.jcgc.platzimarket.domain.Product;
import com.jcgc.platzimarket.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(summary = "Get all supermarket products")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a product with an ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "product not found")
    public ResponseEntity<Product> getProduct(@Parameter(description = "The ID of the product", required = true, example = "7")
                                                  @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Search a category with an ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "category not found")
    public  ResponseEntity<List<Product>> getByCategory(@Parameter(description = "The ID of the category", required = true, example = "1")
                                                            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Save a product inside the DB")
    @ApiResponse(responseCode = "201", description = "Product created")
    public ResponseEntity<Product> save(@Parameter(description = "The product json", required = true,
            example = """
                    {
                      "productId": 0,
                      "name": "string product name",
                      "categoryId": 0,
                      "price": 0,
                      "stock": 0,
                      "active": true,
                      "category": {
                        "categoryId": 0,
                        "category": "string product category",
                        "active": true
                      }
                    }
                    """)
                                            @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a product with an ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "product not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete (@Parameter(description = "The ID of the product", required = true, example = "1")
                                               @PathVariable("id") int productId) {
        return productService.delete(productId) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
