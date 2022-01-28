package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product get(Long id) {
        //TODO: lançar exceção se nulo
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product p) {
        return productRepository.save(p);
    }
}
