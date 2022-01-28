package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.dtos.SectionDto;
import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private ProductAnnouncementRepository productAnnouncementRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Batch> create(Inbound inbound) {
        Section s = sectionService.get(inbound.getSection().getId());
        inbound.setSection(s);
        inboundRepository.save(inbound);
        return inbound.getBatchList();
    }
    public Inbound get(Long id){
        return inboundRepository.findById(id).orElse(null);
    }
}
