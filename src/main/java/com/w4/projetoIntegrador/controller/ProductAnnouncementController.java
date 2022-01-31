package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.service.ProductAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productannouncements")
public class ProductAnnouncementController {

    @Autowired
    ProductAnnouncementService productAnnouncementService;

    @GetMapping("/{id}")
    public ProductAnnouncement getProduct(@PathVariable Long id){
        return productAnnouncementService.get(id);
    }

    @PostMapping()
    public ResponseEntity<ProductAnnouncement> newProduct(@RequestBody ProductAnnouncement p){
        return ResponseEntity.status(201).body(productAnnouncementService.save(p));
    }
}
