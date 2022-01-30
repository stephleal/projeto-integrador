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
            Section section = sectionRepository.findById(id).orElse(null);
            section.setWarehouseId(section.getWarehouse().getId());
            return section;
        }

        public Section save(Section section) {
           Warehouse w = warehouseService.get(section.getWarehouseId());
           section.setWarehouse(w);
           return sectionRepository.save(section);
        }
    }

