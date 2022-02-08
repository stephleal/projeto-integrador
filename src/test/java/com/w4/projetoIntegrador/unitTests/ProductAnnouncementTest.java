package com.w4.projetoIntegrador.unitTests;

import com.w4.projetoIntegrador.dtos.ProductAnnouncementDto;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.Seller;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import com.w4.projetoIntegrador.service.ProductAnnouncementService;
import com.w4.projetoIntegrador.service.ProductService;
import com.w4.projetoIntegrador.service.SellerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class ProductAnnouncementTest {

    private Product product1 = Product.builder().id(1L).name("product").productType(ProductTypes.congelado).build();
    private Seller seller1 = Seller.builder().id(1l).name("seller").build();
    private ProductAnnouncement pa1 = ProductAnnouncement.builder()
            .id(1l)
            .name("product")
            .brand("brand")
            .price(new BigDecimal(1))
            .volume(10f)
            .minimumTemperature(-10f)
            .maximumTemperature(0f)
            .product(product1)
            .seller(seller1)
            .build();

    private ProductAnnouncementDto paDto1 = ProductAnnouncementDto.builder()
            .id(1l)
            .name("product")
            .brand("brand")
            .price(new BigDecimal(1))
            .volume(10f)
            .minimumTemperature(-10f)
            .maximumTemperature(0f)
            .productId(1L)
            .sellerId(1l)
            .build();

    @Test
    public void deveObterUmProductAnnouncement() {

        //arrange
        ProductAnnouncementRepository mockProductAnnouncementRepository = Mockito.mock(ProductAnnouncementRepository.class);
        ProductService mockProductService = Mockito.mock(ProductService.class);
        SellerService mockSellerService = Mockito.mock(SellerService.class);
        Mockito.when(mockProductAnnouncementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pa1));

        ProductAnnouncementService productAnnouncementService = new ProductAnnouncementService(mockProductService, mockProductAnnouncementRepository, mockSellerService);

        //act
        ProductAnnouncementDto paDto = productAnnouncementService.get(1l);

        //assertion
        assertTrue(paDto.getName().equals(pa1.getName()));
    }

    @Test
    public void deveCadastarUmProductAnnouncement() {

        //arrange
        ProductAnnouncementRepository mockProductAnnouncementRepository = Mockito.mock(ProductAnnouncementRepository.class);
        ProductService mockProductService = Mockito.mock(ProductService.class);
        SellerService mockSellerService = Mockito.mock(SellerService.class);
        Mockito.when(mockProductAnnouncementRepository.save(Mockito.any())).thenReturn(pa1);
        Mockito.when(mockProductService.getProduct(Mockito.anyLong())).thenReturn(product1);
        Mockito.when(mockSellerService.getSeller(Mockito.anyLong())).thenReturn(seller1);
        ProductAnnouncementService productAnnouncementService = new ProductAnnouncementService(mockProductService, mockProductAnnouncementRepository, mockSellerService);

        //act
        ProductAnnouncementDto paDto = productAnnouncementService.save(paDto1);

        //assertion
        assertTrue(paDto.getName().equals(pa1.getName()));
    }
}