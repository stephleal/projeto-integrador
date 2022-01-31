package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;

    @Autowired
    private ProductAnnouncementService productAnnouncementService;

    @Autowired
    private SectionService sectionService;

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

    public List<Batch> update(Long id, Inbound inbound){
        Section s = sectionService.get(inbound.getSectionId());
        Inbound foundedInbounded = inboundRepository.findById(id).orElse(null);
        foundedInbounded.setSection(s);

        foundedInbounded.setBatchList(null);

        for (Batch batch:inbound.getBatchList()){
            ProductAnnouncement pa = productAnnouncementService.get(batch.getProductId());
            batch.setProductAnnouncement(pa);
            batch.setStock(batch.getInitialQuantity());
            batch.setInbound(foundedInbounded);
        }

        foundedInbounded.setBatchList(inbound.getBatchList());

        foundedInbounded.setDate(inbound.getDate());

        //inboundRepository.saveAndFlush(foundedInbounded);
        return foundedInbounded.getBatchList();
    }
}
