package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.ValidDueDateProductsDto;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/sections")
public class SectionController {


    @Autowired
    SectionService sectionService;

    @GetMapping("/{id}")
    public Section getSection(@PathVariable Long id) {

        return sectionService.get(id);
    }

    @PostMapping()
    public  ResponseEntity<Section> newSection(@Valid @RequestBody Section s) {

        return ResponseEntity.status(201).body(sectionService.save(s));
    }

    @GetMapping("/valid/section/{id}/days/{days}")
    public ResponseEntity<List<ValidDueDateProductsDto>> getValidDueDateProducts(@PathVariable Long id, @PathVariable Integer days) {
        return ResponseEntity.status(200).body(sectionService.getValidDueDateProducts(id, days));
    }

    @GetMapping("/valid/category/{category}/days/{days}")
    public ResponseEntity<List<ValidDueDateProductsDto>> getValidDueDateProductsByCategory(@PathVariable String category, @PathVariable Integer days, @RequestParam String orderBy) {
        return ResponseEntity.status(200).body(sectionService.getValidDueDateProductsByCategory(category, days, orderBy));
    }
}

