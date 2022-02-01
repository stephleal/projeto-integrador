package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;

    @Autowired
    private ProductAnnouncementService productAnnouncementService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private BatchService batchService;

    public List<Batch> create(Inbound inbound) {
        Section s = sectionService.get(inbound.getSectionId());
        inbound.setSection(s);

        for (Batch batch:inbound.getBatchList()){
            ProductAnnouncement pa = productAnnouncementService.get(batch.getProductId());
            batch.setProductAnnouncement(pa);
            batch.setStock(batch.getInitialQuantity());
            batch.setInbound(inbound);
        }

        inboundRepository.save(inbound);
        return inbound.getBatchList();
    }

    public Inbound get(Long id){
       try {
        Inbound inbound = inboundRepository.findById(id).orElse(null);
        return inbound;
       } catch (RuntimeException e) {
           throw new NotFoundException("Inbound order " + id + " não encontrado na base de dados.");
       }
    }

    public Inbound update(Long id, Inbound inbound){
        Inbound foundedInbound = inboundRepository.findById(id).orElse(null);
        foundedInbound.setSectionId(inbound.getSectionId());
        foundedInbound.setDate(inbound.getDate());
        List<Batch> newBatchList = new ArrayList<>();

        for (Batch payloadBatch:inbound.getBatchList()){
            Batch foundedBatch = batchService.get(payloadBatch.getId());
            if (foundedBatch.getInbound().getId() != id) throw new BusinessException("Id de batch não corresponde ao inbound");
            foundedBatch.setInitialQuantity(payloadBatch.getInitialQuantity());
            foundedBatch.setType(payloadBatch.getType());
            foundedBatch.setProductAnnouncement(productAnnouncementService.get(payloadBatch.getProductId()));
            Integer sold = foundedBatch.getInitialQuantity() - foundedBatch.getStock();
            foundedBatch.setStock(payloadBatch.getInitialQuantity() - sold);
            foundedBatch.setManufacturingDateTime(payloadBatch.getManufacturingDateTime());
            foundedBatch.setDueDate(payloadBatch.getDueDate());
            foundedBatch.setCurrentTemperature(payloadBatch.getCurrentTemperature());
            foundedBatch.setInbound(foundedInbound);
            newBatchList.add(foundedBatch);
        }

        foundedInbound.setBatchList(newBatchList);

        inboundRepository.save(foundedInbound);
        return foundedInbound;
    }
}