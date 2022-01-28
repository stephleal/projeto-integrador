package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.BatchDto;
import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.dtos.SectionDto;
import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InboundService {

    @Autowired
    private InboundRepository inboundRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private ProductAnnouncementRepository productAnnouncementRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Batch> create(InboundDto idto) {

        Product p1 = productRepository.findById(1l).orElse(null);
        Product p2 = productRepository.findById(2l).orElse(null);
        Product p3 = productRepository.findById(3l).orElse(null);
        Product p4 = productRepository.findById(4l).orElse(null);


        ProductAnnouncement pAn1 = ProductAnnouncement.builder()
                .id(null)
                .product(p1)
                .name("presunto sadia")
                .brand("sadia")
                .price(new BigDecimal(20))
                .volume(1f)
                .minimumTemperature(2f)
                .maximumTemperature(5f)
                .build();

        ProductAnnouncement pAn2 = ProductAnnouncement.builder()
                .id(null)
                .product(p2)
                .name("presunto sadia")
                .brand("sadia")
                .price(new BigDecimal(20))
                .volume(1f)
                .minimumTemperature(2f)
                .maximumTemperature(5f)
                .build();

        ProductAnnouncement pAn3 = ProductAnnouncement.builder()
                .id(null)
                .product(p3)
                .name("presunto sadia")
                .brand("sadia")
                .price(new BigDecimal(20))
                .volume(1f)
                .minimumTemperature(2f)
                .maximumTemperature(5f)
                .build();

        ProductAnnouncement pAn4 = ProductAnnouncement.builder()
                .id(null)
                .product(p4)
                .name("presunto sadia")
                .brand("sadia")
                .price(new BigDecimal(20))
                .volume(1f)
                .minimumTemperature(2f)
                .maximumTemperature(5f)
                .build();

        productAnnouncementRepository.save(pAn1);
        productAnnouncementRepository.save(pAn2);
        productAnnouncementRepository.save(pAn3);
        productAnnouncementRepository.save(pAn4);

        Batch b1 = Batch.builder()
                .currentTemperature(23F)
                .dueDate(LocalDate.now())
                .initialQuantity(2)
                .manufacturingDateTime(LocalDateTime.now())
                .productAnnouncement(pAn1)
                .stock(5)
                .type(ProductTypes.cold).build();

        Batch b2 = Batch.builder()
                .currentTemperature(23F)
                .dueDate(LocalDate.now())
                .initialQuantity(2)
                .manufacturingDateTime(LocalDateTime.now())
                .productAnnouncement(pAn2)
                .stock(5)
                .type(ProductTypes.cold).build();

        List<Batch> batch = new ArrayList<Batch>();
        batch.add(b1);
        batch.add(b2);

        Warehouse w = Warehouse.builder().id(null).name("armazen").build();
        Section s = Section.builder().id(null).type(ProductTypes.cold).totalSpace(200f).warehouse(w).build();

        Inbound i = Inbound.builder()
                .id(null)
                .date(LocalDateTime.now())
                .section(s)
                .batchList(batch)
                .build();

        //inboundRepository.save(i);

        return batch;
    }
}
