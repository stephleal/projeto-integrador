package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.ProductsByWarehouseDto;
import com.w4.projetoIntegrador.dtos.WarehouseStockDto;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ProductsByWarehouseDto getWarehouseStock(Long id){
       List<WarehouseRepository.ProductWarehouse> list = warehouseRepository.getStockByWarehouse(id);
       ProductsByWarehouseDto pto = ProductsByWarehouseDto.builder().productId(id)
               .build();
       List<WarehouseStockDto> wd = new ArrayList<>();
        for (WarehouseRepository.ProductWarehouse item:list){
            WarehouseStockDto ws = WarehouseStockDto.builder().warehosecode(item.getWarehouse()).totalquantity(item.getStock()).build();
            wd.add(ws);
        }
        pto.setWarehouses(wd);
        return pto;
    }
}

