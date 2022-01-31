package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Warehouse> newWarehouse(@Valid @RequestBody Warehouse wh) {

        return ResponseEntity.status(201).body(warehouseService.save(wh));
    }
}

