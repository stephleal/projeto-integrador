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
        Batch batch = batchRepository.findById(id).orElse(null);
        batch.setProductId(batch.getProductAnnouncement().getId());
        return batch;
    }

    public Batch save(Batch batch) {
        ProductAnnouncement productAnnouncement = productAnnouncementService.get(batch.getProductId());
        batch.setProductAnnouncement(productAnnouncement);
        batch.setStock(batch.getInitialQuantity());
        return batchRepository.save(batch);
    }
}
