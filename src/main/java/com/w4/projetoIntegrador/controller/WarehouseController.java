package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Long id) {

        return warehouseService.get(id);
    }

    @PostMapping()
    public Warehouse newWarehouse(@RequestBody Warehouse wh) {

        return warehouseService.save(wh);
    }
}

