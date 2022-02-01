package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Buyer;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    public Buyer getBuyer(Long id) {
        try {
            return buyerRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Comprador " + id + " não encontrado na base de dados.");
        }
    }

    public Buyer create(Buyer buyer) {

        return buyerRepository.save(buyer);
    }
}
