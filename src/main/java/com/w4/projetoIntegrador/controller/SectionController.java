package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Section newSection(@RequestBody Section s) {

        return sectionService.save(s);
    }
}

