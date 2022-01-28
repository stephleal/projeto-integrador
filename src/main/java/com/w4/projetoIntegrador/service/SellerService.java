package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public Seller get(Long id){
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller save(Seller seller){
        return sellerRepository.save(seller);
    }
}
