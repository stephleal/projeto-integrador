package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAnnouncementService {

    @Autowired
    ProductService productService;

    @Autowired
    ProductAnnouncementRepository productAnnouncementRepository;

    public ProductAnnouncement get(Long id){
        //TODO: lançar exceção
        return productAnnouncementRepository.findById(id).orElse(null);

    }

    public ProductAnnouncement save(ProductAnnouncement pAn){
        Product p = productService.get(pAn.getProductId());
                ProductAnnouncement pAn2 = ProductAnnouncement.builder()
                .id(null)
                .name(pAn.getName())
                .product(p)
                .maximumTemperature(pAn.getMaximumTemperature())
                .minimumTemperature(pAn.getMinimumTemperature())
                .seller(Seller.builder().id(1l).name("josé").build())
                .volume(pAn.getVolume())
                .price(pAn.getPrice())
                .brand(pAn.getBrand())
                .build();
        //Todo implementar sevice de seller para validar

       return productAnnouncementRepository.save(pAn2);
    }

}
