package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.*;
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
        Section s = sectionService.get(inbound.getSection().getId());
        inbound.setSection(s);
        for (Batch batch:inbound.getBatchList()){
            ProductAnnouncement pa = productAnnouncementService.get(batch.getProductId());
            batch.setProductAnnouncement(pa);
            batch.setInbound(inbound);
        }
        inboundRepository.save(inbound);
        return inbound.getBatchList();
    }
    public Inbound get(Long id){
        return inboundRepository.findById(id).orElse(null);
    }
}
