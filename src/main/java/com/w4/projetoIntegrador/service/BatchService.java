package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.repository.BatchRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    ProductAnnouncementService productAnnouncementService;

    public Batch get(Long id) {
        return batchRepository.findById(id).orElse(null);
    }

    public Batch save(Batch batch) {
        ProductAnnouncement productAnnouncement = productAnnouncementService.get(batch.getProductId());
        batch.setProductAnnouncement(productAnnouncement);
        return batchRepository.save(batch);
    }
}
