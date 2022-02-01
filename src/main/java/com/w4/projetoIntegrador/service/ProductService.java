package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dto.ProductLocationDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BatchRepository;
import com.w4.projetoIntegrador.repository.InboundRepository;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import com.w4.projetoIntegrador.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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


    public Product get(Long id) {
        try {
            return productRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrada na base de dados.");
        }
    }

    public Product save(Product p) {

        return productRepository.save(p);
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

        productListByCategory = productListByCategory
                .stream()
                .filter(product -> ProductTypes
                        .values()[Integer.parseInt(product.getProductType())]
                        .equals(ProductTypes.valueOf(category)))
                .collect(Collectors.toList());

        if (productListByCategory.size() == 0)
            throw new NotFoundException("Não existem produtos cadastrados nessa categoria na base de dados");

        return productListByCategory;
    }

    public List<Batch> getProductLocation(Long id) {
        System.out.println("Cheguei aqui no service do produto");
        ProductAnnouncement product = productAnnouncementRepository.findById(id).orElse(null);
        
        List<Batch> batchesList = batchRepository.findByProductAnnouncement(product);
        for (Batch batch : batchesList) {
            batch.setProductId(id);
        }

        return batchesList;
    }
}

