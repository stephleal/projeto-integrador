package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.get(id);
    }

    @PostMapping()
    public ResponseEntity<Product> newProduct(@Valid @RequestBody Product p){
        return ResponseEntity.status(201).body(productService.save(p));
    }
}
