package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;

    @Autowired
    private ProductAnnouncementService productAnnouncementService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private BatchService batchService;

    public InboundDto create(InboundDto inboundDto) {
        try {
            Section s = sectionService.getSection(inboundDto.getSectionId());
            Agent agent = agentService.getAgent(inboundDto.getAgentId());

            if (!agent.getSection().getId().equals(s.getId())) throw new BusinessException("O representante não pertence a este setor");
            List<Batch> batchList = new ArrayList<>();
            Float inboundVolume = 0F;

            for (BatchDto batchDto : inboundDto.getBatchDtoList()) {
                ProductAnnouncement pa = productAnnouncementService.getProductAnnouncement(batchDto.getProductId());
                inboundVolume += pa.getVolume() * batchDto.getInitialQuantity();
                checkTypeMatch(pa.getProduct().getProductType(), s.getType());
                Batch batch = BatchDto.convert(batchDto, pa, batchDto.getInitialQuantity());
                batchList.add(batch);
            }
            checkSectionCapacity(s,inboundVolume);
            Inbound inbound = InboundDto.convert(inboundDto, batchList, s);
            inbound.setAgent(agent);
            inbound.getBatchList().stream().forEach(batch -> batch.setInbound(inbound));
            inboundRepository.save(inbound);

            return InboundDto.convert(inbound);
        }catch (RuntimeException e){
            throw new BusinessException(e.getMessage());
        }
    }

    public InboundDto get(Long id){
       try {
        Inbound inbound = inboundRepository.findById(id).orElse(null);
        return InboundDto.convert(inbound);
       } catch (RuntimeException e) {
           throw new NotFoundException("Inbound order " + id + " não encontrado na base de dados.");
       }
    }

   public InboundDto update(Long id, InboundDto inbound){
       Inbound foundedInbound = inboundRepository.findById(id).orElse(null);
       foundedInbound.setSection(sectionService.getSection(inbound.getSectionId()));
       foundedInbound.setDate(inbound.getDate());
       List<Batch> newBatchList = new ArrayList<>();

       for (BatchDto payloadBatch:inbound.getBatchDtoList()){
           Batch foundedBatch = batchService.getBatch(payloadBatch.getId());
           if (foundedBatch.getInbound().getId() != id) throw new BusinessException("Id de batch não corresponde ao inbound");
           foundedBatch.setProductAnnouncement(productAnnouncementService.getProductAnnouncement(payloadBatch.getProductId()));
           Integer sold = foundedBatch.getInitialQuantity() - foundedBatch.getStock();
           foundedBatch.setStock(payloadBatch.getInitialQuantity() - sold);
           foundedBatch.setInitialQuantity(payloadBatch.getInitialQuantity());
           foundedBatch.setManufacturingDateTime(payloadBatch.getManufacturingDateTime());
           foundedBatch.setDueDate(payloadBatch.getDueDate());
           foundedBatch.setCurrentTemperature(payloadBatch.getCurrentTemperature());
           foundedBatch.setInbound(foundedInbound);
           newBatchList.add(foundedBatch);
       }
       foundedInbound.setBatchList(newBatchList);
       inboundRepository.save(foundedInbound);
       return inbound;
   }

    private void checkTypeMatch(ProductTypes p1, ProductTypes p2){
        if (!p1.equals(p2)) {
            String message = "Um produto " + p1 + " não pode ser armazenado em um setor de " + p2;
            throw new BusinessException(message);
        }
    }

    private void checkSectionCapacity(Section s, Float inboundVolume){
        List<InboundRepository.SectionsCapacity> capacitySections = inboundRepository.findCapacityAllSections();

        List<InboundRepository.SectionsCapacity> sectionsCapacity = capacitySections.stream().filter(cap -> cap.getId().equals(s.getId())).collect(Collectors.toList());
        if(sectionsCapacity.size() == 0) return;

        Float sectionCurrentVolume = capacitySections.stream().filter(cap -> cap.getId().equals(s.getId())).collect(Collectors.toList()).get(0).getVolume();
        Float availableSectionVolume = s.getTotalSpace() - sectionCurrentVolume;
        if(inboundVolume > availableSectionVolume) throw new BusinessException("Não há espaço disponível neste setor");
    }
}