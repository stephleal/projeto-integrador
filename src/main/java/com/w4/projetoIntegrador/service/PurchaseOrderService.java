package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.PurchaseOrder;
import com.w4.projetoIntegrador.entities.PurchaseProduct;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    public List<ProductAnnouncement> getPurchaseOrder(Long id) {
        try {
            List<PurchaseProduct> purchaseProducts = purchaseOrderRepository.findById(id).orElse(null).getPurchaseProducts();
            List<ProductAnnouncement> productAnnouncements = new ArrayList<ProductAnnouncement>();
            for (PurchaseProduct p : purchaseProducts ) {
                productAnnouncements.add(p.getProductAnnouncement());
            }
            return productAnnouncements;
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrada na base de dados.");
        }
    }

    public PurchaseOrder create(PurchaseOrder purchaseOrder) {

        return purchaseOrderRepository.save(purchaseOrder);
    }
}
