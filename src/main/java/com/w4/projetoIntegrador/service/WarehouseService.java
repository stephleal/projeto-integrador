package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse get(Long id) {
        //TODO: lançar exceção se nulo
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }
}

