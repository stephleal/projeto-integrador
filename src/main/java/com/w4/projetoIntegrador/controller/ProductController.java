package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.ProductLocationDto;

import com.w4.projetoIntegrador.dtos.ProductDto;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Product> newProduct(@Valid @RequestBody ProductDto p){
        return ResponseEntity.status(201).body(productService.save(p));
    }

    @GetMapping("/")
    public List<Product> getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/list")
    public List<Product> getProductlistByCategory(@RequestParam String category){
        return productService.getProductListByCategory(category);
    }

    @GetMapping("/loc")
    public ResponseEntity<ProductLocationDto> getProductLocation(@RequestParam Long id, @RequestParam(required = false) Character orderBy) {
        return ResponseEntity.status(200).body(productService.orderProductByCategory(id,orderBy));
    }
}