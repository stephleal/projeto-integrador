package com.w4.projetoIntegrador.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.ValidDueDateProductsDto;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.SectionRepository;
import com.w4.projetoIntegrador.repository.SectionRepository.ValidDueDateProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Local;

@Service
public class SectionService {

        @Autowired
        SectionRepository sectionRepository;

        @Autowired
        WarehouseService warehouseService;

        public Section get(Long id) {
            try {
                Section section = sectionRepository.findById(id).orElse(null);
                section.setWarehouseId(section.getWarehouse().getId());
                return section;
            } catch (RuntimeException e) {
                throw new NotFoundException("Section " + id + " não encontrada na base de dados.");
            }
        }

        public Section save(Section section) {
           Warehouse w = warehouseService.get(section.getWarehouseId());
           section.setWarehouse(w);
           return sectionRepository.save(section);
        }

        public List<ValidDueDateProductsDto> getValidDueDateProducts(Long id, Integer dias) {
            List<SectionRepository.ValidDueDateProducts> validProducts = sectionRepository.findValidDueDateProducts(id);
            List<ValidDueDateProductsDto> validDueDateList = new ArrayList<ValidDueDateProductsDto>();
            for (ValidDueDateProducts validDueDateProducts : validProducts) {
                if (LocalDate.now().plusDays(dias).isAfter(validDueDateProducts.getDueDate())) {
                 ValidDueDateProductsDto validDueDateProductsDto = ValidDueDateProductsDto.builder()
                 .batchNumber(validDueDateProducts.getBatchNumber())
                 .productId(validDueDateProducts.getProductId())
                 .productTypeId(validDueDateProducts.getProductTypeId())
                 .dueDate(validDueDateProducts.getDueDate())
                 .quantity(validDueDateProducts.getQuantity())
                 .build();

                 validDueDateList.add(validDueDateProductsDto);
                } 
            }
            return validDueDateList;
        }
    }

