package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.SellerDto;
import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSeller(Long id) {
        try {
            Seller seller = sellerRepository.findById(id).orElse(null);
            return seller;
        } catch (RuntimeException e) {
            throw new NotFoundException("Seller " + id + " não encontrado na base de dados.");
        }
    }

    public SellerDto get(Long id) {
        Seller seller = getSeller(id);
        try {
            return SellerDto.convert(seller);
        } catch (RuntimeException e) {
            throw new NotFoundException("Seller " + id + " não encontrado na base de dados.");
        }

    }

    public SellerDto save(SellerDto sellerDto) {
        sellerRepository.save(SellerDto.convert(sellerDto));
        return sellerDto;
    }
}
