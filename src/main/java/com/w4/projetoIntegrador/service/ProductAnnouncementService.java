package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAnnouncementService {

    @Autowired
    ProductService productService;

    @Autowired
    ProductAnnouncementRepository productAnnouncementRepository;

    @Autowired
    SellerService sellerService;

    public ProductAnnouncement get(Long id){
    try {
        ProductAnnouncement productAnnouncement = productAnnouncementRepository.findById(id).orElse(null);
        productAnnouncement.setProductId(productAnnouncement.getProduct().getId());
        productAnnouncement.setSellerId(productAnnouncement.getSeller().getId());
        return productAnnouncement;
    } catch (RuntimeException e) {
        throw new NotFoundException("ProductAnnouncement  " + id + " não encontrada na base de dados.");
    }
    }

    public ProductAnnouncement save(ProductAnnouncement productAnnouncement){
        Product p = productService.get(productAnnouncement.getProductId());
        Seller seller = sellerService.get(productAnnouncement.getSellerId());
        productAnnouncement.setProduct(p);
        productAnnouncement.setSeller(seller);

        //Todo implementar sevice de seller para validar

       return productAnnouncementRepository.save(productAnnouncement);
    }

}
