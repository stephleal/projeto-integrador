package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.BuyerDto;
import com.w4.projetoIntegrador.entities.Buyer;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }

    public BuyerDto get(Long id) {
        return BuyerDto.convert(getBuyer(id));
    }

    public Buyer getBuyer(Long id) {
        try {
            return buyerRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Comprador " + id + " não encontrado na base de dados.");
        }
    }

    public BuyerDto create(BuyerDto buyerDto) {
        Buyer buyer = buyerRepository.save(BuyerDto.convert(buyerDto));
        buyerDto.setId(buyer.getId());
        return buyerDto;
    }
}
