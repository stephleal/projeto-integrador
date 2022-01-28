package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.dtos.SectionDto;
import com.w4.projetoIntegrador.entities.Batch;
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

    @GetMapping("/")
    public InboundDto teste() {

        BatchDto b1 = BatchDto.builder().currentTemperature(23F).dueDate(LocalDate.now()).initialQuantity(2).
                manufacturingDate(LocalDateTime.now()).product_id(2).currentQuantity(4).type(ProductTypes.cold).build();

        BatchDto b2 = BatchDto.builder().currentTemperature(25F).dueDate(LocalDate.now()).initialQuantity(3).
                manufacturingDate(LocalDateTime.now()).product_id(3).currentQuantity(5).type(ProductTypes.frozen).build();

        List<BatchDto> batch = new ArrayList<BatchDto>();
        batch.add(b1);
        batch.add(b2);

        SectionDto s = SectionDto.builder().id(6L).warehouseCode("7").build();

        InboundDto idto = InboundDto.builder().
                orderNumber(234L).orderDate(LocalDateTime.now()).batchStock(batch).section(s).build();
        //inboundService.create(idto);
        return idto;
    }

    @PostMapping
    public ResponseEntity<List<BatchDto>> cadastra(@RequestBody InboundDto idto) {
    return ResponseEntity.ok().body(idto.getBatchStock());
        //return ResponseEntity.ok(inboundService.create(idto));
    }

}
