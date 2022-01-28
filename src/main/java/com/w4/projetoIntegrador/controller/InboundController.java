package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.dtos.SectionDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inboundorder")
public class InboundController {

    @Autowired
    InboundService inboundService;

    @GetMapping("/{id}")
    public Inbound getInbound(@PathVariable Long id) {
        return inboundService.get(id);
        }

    @PostMapping
    public ResponseEntity<List<Batch>> cadastra(@RequestBody Inbound inbound) {
    return ResponseEntity.ok().body(inboundService.create(inbound));
    }

}
