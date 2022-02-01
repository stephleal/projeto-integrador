package com.w4.projetoIntegrador.controller;


import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.PurchaseOrder;
import com.w4.projetoIntegrador.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @GetMapping("/{id}")
    public List<ProductAnnouncement> getPurchaseOrder(@PathVariable Long id){
        return purchaseOrderService.getPurchaseOrder(id);
    }

    @PostMapping()
    public ResponseEntity<String> createPurchaseOrder (@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(201).body(purchaseOrderService.create(purchaseOrder));
    }
}
