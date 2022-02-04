package com.w4.projetoIntegrador.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.w4.projetoIntegrador.dtos.ValidDueDateProductsDto;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.SectionRepository;
import com.w4.projetoIntegrador.repository.SectionRepository.ValidDueDateProducts;
import com.w4.projetoIntegrador.repository.SectionRepository.ValidDueDateProductsByCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
           Warehouse w = warehouseService.getWarehouse(section.getWarehouse().getId());
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

        public List<ValidDueDateProductsDto> getValidDueDateProductsByCategory(String type, Integer days, String orderBy) {

            Integer id = ProductTypes.valueOf(type).ordinal();
            System.out.println(id);
            List<SectionRepository.ValidDueDateProductsByCategory> validProductsByCategory ;

            switch(orderBy) {
                case "asc":
                validProductsByCategory = sectionRepository.findValidDueDateProductsByCategoryAsc(id);
                break;

                case "desc":
                validProductsByCategory = sectionRepository.findValidDueDateProductsByCategoryDesc(id);
                break;

                default:
                throw new BusinessException("criterio deve ser asc ou desc");
            }

            List<ValidDueDateProductsDto> validDueDateListByCategory = new ArrayList<ValidDueDateProductsDto>();
            for (ValidDueDateProductsByCategory validDueDateProductsByCategory : validProductsByCategory) {
                if (LocalDate.now().plusDays(days).isAfter(validDueDateProductsByCategory.getDueDate())) {
                 ValidDueDateProductsDto validDueDateProductsDto = ValidDueDateProductsDto.builder()
                 .batchNumber(validDueDateProductsByCategory.getBatchNumber())
                 .productId(validDueDateProductsByCategory.getProductId())
                 .productTypeId(validDueDateProductsByCategory.getProductTypeId())
                 .dueDate(validDueDateProductsByCategory.getDueDate())
                 .quantity(validDueDateProductsByCategory.getQuantity())
                 .build(); 

                 validDueDateListByCategory.add(validDueDateProductsDto);
                } 
            }
            return validDueDateListByCategory;
        }
    }

