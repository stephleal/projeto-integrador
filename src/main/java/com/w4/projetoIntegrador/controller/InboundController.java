package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.enums.ProductTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inboundorder")
public class InboundController {

    @GetMapping("/")
    public InboundDto teste () {

        Batch b1 = Batch.builder().id(23L).currentTemperature(23F).dueDate(LocalDate.now()).initialQuantity(2).
                manufacturingDateTime(LocalDateTime.now()).productId(2).stock(4).type(ProductTypes.cold).build();

        Batch b2 = Batch.builder().id(25L).currentTemperature(25F).dueDate(LocalDate.now()).initialQuantity(3).
                manufacturingDateTime(LocalDateTime.now()).productId(3).stock(5).type(ProductTypes.frozen).build();

        List<Batch> batch = new ArrayList<Batch>();
        batch.add(b1);
        batch.add(b2);

        Section s = Section.builder().id(6).warehouseCode("7").totalSpace(4).build();

        InboundDto idto = InboundDto.builder().
        orderNumber(234L).orderDate(LocalDateTime.now()).batchStock(batch).section(s).build();

        return idto;
    }

    @PostMapping
    public ResponseEntity<List<Batch>> cadastra(@RequestBody InboundDto idto){
        List<Batch> lb = idto.getBatchStock();
        return ResponseEntity.ok(lb);
    }
}
