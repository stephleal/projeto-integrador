package com.w4.projetoIntegrador.controller;


import com.w4.projetoIntegrador.entities.ItemCart;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.Cart;
import com.w4.projetoIntegrador.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{id}")
    public List<ItemCart> getPurchaseOrder(@PathVariable Long id){
        return cartService.getCart(id);
    }

    @PostMapping()
    public ResponseEntity<String> createPurchaseOrder (@RequestBody Cart cart) {
        return ResponseEntity.status(201).body(cartService.create(cart));
    }
}
