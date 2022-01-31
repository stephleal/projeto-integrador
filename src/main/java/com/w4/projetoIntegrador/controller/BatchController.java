package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.service.BatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;


    
    @GetMapping("/{id}")
    public Batch getBatch(@PathVariable Long id){
        return batchService.get(id);
    }

    @PostMapping()
    public ResponseEntity<Batch> newBatch(@Valid @RequestBody Batch batch) {
        return ResponseEntity.status(201).body(batchService.save(batch));
    }

}
