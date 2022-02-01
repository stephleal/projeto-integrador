package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BuyerRepository;
import com.w4.projetoIntegrador.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    BuyerService buyerService;

    public List<ProductAnnouncement> getPurchaseOrder(Long id) {

        try {
            List<PurchaseProduct> purchaseProducts = purchaseOrderRepository.findById(id).orElse(new PurchaseOrder()).getPurchaseProducts();

            System.out.println(purchaseProducts);
            List<ProductAnnouncement> productAnnouncements = new ArrayList<ProductAnnouncement>();
            for (PurchaseProduct p : purchaseProducts) {
                productAnnouncements.add(p.getProductAnnouncement());
            }
            return productAnnouncements;
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrado na base de dados.");
        }
    }

    public String create(PurchaseOrder purchaseOrder) {

        Buyer buyer = buyerService.getBuyer(purchaseOrder.getBuyerId());
        purchaseOrder.setBuyer(buyer);

        String value = "0";
        BigDecimal total = new BigDecimal(0);

        List<ProductAnnouncement> productAnnouncements = getPurchaseOrder(purchaseOrder.getId());

        System.out.println(productAnnouncements);

        for (int i = 0; i < productAnnouncements.size(); i++) {
            System.out.println(productAnnouncements.get(i));
          //  value = value.add(new BigDecimal(String.valueOf(productAnnouncements.get(i).getPrice())));//add(new BigDecimal(String.valueOf(value))));

            value = (String.valueOf(productAnnouncements.get(i).getPrice().add(new BigDecimal(String.valueOf(value)))));

            BigDecimal valor = new BigDecimal(String.valueOf(productAnnouncements.get(i).getPrice().add(new BigDecimal(String.valueOf(total)))));

            total.add(valor);

            System.out.println(valor);
        }

        System.out.println(total);

        return String.valueOf(total);
    }
}
