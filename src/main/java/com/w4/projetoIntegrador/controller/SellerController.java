package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
public class SellerController {

        @Autowired
        SellerService sellerService;

        @GetMapping("/{id}")
        public Seller getSeller(@PathVariable Long id){
            return sellerService.get(id);
        }

        @PostMapping()
        public Seller newSeller(@RequestBody Seller s){
            return sellerService.save(s);
        }
    }

