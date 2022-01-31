package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse get(Long id) {
        try {
            return warehouseRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Warehouse " + id + " não encontrada na base de dados.");
        }
    }

    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }
}

