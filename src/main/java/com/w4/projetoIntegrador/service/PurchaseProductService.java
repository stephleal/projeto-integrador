package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.PurchaseProduct;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.PurchaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseProductService {

    @Autowired
    PurchaseProductRepository purchaseProductRepository;

    public PurchaseProduct getPurchaseProduct(Long id) {
        try {
            return purchaseProductRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrada na base de dados.");
        }
    }

    public PurchaseProduct create(PurchaseProduct purchaseProduct) {
        return purchaseProductRepository.save(purchaseProduct);
    }
}

