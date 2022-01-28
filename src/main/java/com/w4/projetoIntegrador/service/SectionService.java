package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

        @Autowired
        SectionRepository sectionRepository;

        @Autowired
        WarehouseService warehouseService;

        public Section get(Long id) {
            //TODO: lançar exceção se nulo
            return sectionRepository.findById(id).orElse(null);
        }

        public Section save(Section s) {
           Warehouse w = warehouseService.get(s.getWarehouseId());
           Section sec = Section.builder()
                   .warehouse(w)
                   .totalSpace(s.getTotalSpace())
                   .type(s.getType())
                   .build();
           return sectionRepository.save(sec);
        }
    }

