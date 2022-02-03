package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dto.ProductLocationDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;

import com.w4.projetoIntegrador.dtos.ProductDto;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BatchRepository;
import com.w4.projetoIntegrador.repository.InboundRepository;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import com.w4.projetoIntegrador.repository.ProductRepository;
import com.w4.projetoIntegrador.repository.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAnnouncementRepository productAnnouncementRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    InboundRepository inboundRepository;

    @Autowired
    SectionRepository sectionRepository;

    public Product get(Long id) {
        try {
            return productRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrada na base de dados.");
        }
    }

    public Product save(ProductDto p) {
        Product product = ProductDto.convert(p);

        return productRepository.save(product);
    }

    public List<Product> getProductList() {

        List<Product> productList = new ArrayList<Product>();

        productList = productRepository.findAll();

        if (productList.size() == 0)
            throw new NotFoundException("Não existem produtos cadastrados na base de dados");

        return productList;
    }

    public List<Product> getProductListByCategory(String category) {

        List<Product> productListByCategory = getProductList();

//        productListByCategory = productListByCategory
//                .stream()
//                .filter(product -> ProductTypes
//                        .values()[Integer.parseInt(product.getProductType())]
//                        .equals(ProductTypes.valueOf(category)))
//                .collect(Collectors.toList());


        productListByCategory = productListByCategory
                .stream()
                .filter(product -> product.equals(ProductTypes.valueOf(category)))
                .collect(Collectors.toList());


        if (productListByCategory.size() == 0)
            throw new NotFoundException("Não existem produtos cadastrados nessa categoria na base de dados");

        return productListByCategory;
    }

    public ProductLocationDto getProductLocation(Long id) {
        ProductAnnouncement product = productAnnouncementRepository.findById(id).orElse(null);

        List<Batch> batchesList = batchRepository.findByProductAnnouncement(product);
        for (Batch batch : batchesList) {
            batch.setProductId(id);
        }
        Inbound foundedInbound = inboundRepository.getById(id);
        ProductLocationDto productLocationDto = new ProductLocationDto();
        productLocationDto.setBatchStock(batchesList);
        productLocationDto.setProductId(id);
        productLocationDto.setSection(foundedInbound.getSection());
        productLocationDto.getSection().setWarehouseId(foundedInbound.getSection().getWarehouse().getId());
        return productLocationDto;
    }

    public ProductLocationDto orderProductByCategory(Long id, Character ordenation) {
        ProductLocationDto productLocationDto = getProductLocation(id);

        List<Batch> batchList;

        switch (ordenation) {
            case 'L':
                batchList = productLocationDto.getBatchStock().stream().sorted((b1, b2) -> Long.compare(b1.getId(), b2.getId())).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            case 'C':
                batchList = productLocationDto.getBatchStock().stream().sorted((b1, b2) -> Integer.compare(b1.getStock(), b2.getStock())).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            case 'F':
                batchList = productLocationDto.getBatchStock().stream().sorted((b1, b2) -> String.CASE_INSENSITIVE_ORDER.compare(b1.getDueDate().toString(), b2.getDueDate().toString())).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            default:
                return productLocationDto;

        }

    }

}

