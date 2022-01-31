package com.w4.projetoIntegrador.controller;


import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @GetMapping("/{id}")
    public List<ProductAnnouncement> getPurchaseOrder(@PathVariable Long id){

        return purchaseOrderService.getPurchaseOrder(id);
    }
}
