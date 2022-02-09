package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.CartDto;
import com.w4.projetoIntegrador.entities.ItemCart;
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
    public ResponseEntity<CartDto> getCart(@PathVariable Long id){
        return ResponseEntity.ok().body(cartService.get(id));
    }

    @PostMapping()
    public ResponseEntity<CartDto> createCart (@RequestBody CartDto cartDto) {
        return ResponseEntity.status(201).body(cartService.create(cartDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart (@PathVariable Long id, @RequestBody CartDto cartDto){

        return ResponseEntity.status(201).body(cartService.updateCart(id, cartDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartDto> cancelCart (@PathVariable Long id) {
        return ResponseEntity.ok().body(cartService.cancelCart(id));
    }
}
