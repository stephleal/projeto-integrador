package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.ProductLocationDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.dtos.ProductDto;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BatchRepository;
import com.w4.projetoIntegrador.repository.InboundRepository;
import com.w4.projetoIntegrador.repository.ProductAnnouncementRepository;
import com.w4.projetoIntegrador.repository.ProductRepository;
import com.w4.projetoIntegrador.repository.SectionRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    ProductRepository productRepository;

    ProductAnnouncementRepository productAnnouncementRepository;

    BatchRepository batchRepository;

    InboundRepository inboundRepository;

    SectionRepository sectionRepository;

    public ProductService(ProductRepository productRepository,
            ProductAnnouncementRepository productAnnouncementRepository,
            BatchRepository batchRepository,
            InboundRepository inboundRepository,
            SectionRepository sectionRepository){
        this.productRepository = productRepository;
        this.productAnnouncementRepository = productAnnouncementRepository;
        this.batchRepository = batchRepository;
        this.inboundRepository = inboundRepository;
        this.sectionRepository = sectionRepository;
    }

    public Product getProduct(Long id) {
        try {
            return productRepository.findById(id).orElse(null);
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrada na base de dados.");
        }
    }

    public ProductDto get(Long id) {
        return ProductDto.convert(getProduct(id));
    }

    public ProductDto save(ProductDto p) {
        Product product = ProductDto.convert(p);
        productRepository.save(product);
        return p;
    }

    public List<ProductDto> getProductDtoList() {
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product p : getProductList()) {
            productDtoList.add(ProductDto.convert(p));
        }
        return productDtoList;
    }

    private List<Product> getProductList() {

        List<Product> productList = new ArrayList<Product>();

        productList = productRepository.findAll();

        if (productList.size() == 0)
            throw new NotFoundException("Não existem produtos cadastrados na base de dados");

        return productList;
    }

    public List<ProductDto> getProductDtoListByCategory(String category) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product p : getProductListByCategory(category)) {
            productDtoList.add(ProductDto.convert(p));
        }
        return productDtoList;
    }


    private List<Product> getProductListByCategory(String category) {

        List<Product> productListByCategory = getProductList();

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

        if (ordenation == null) return productLocationDto;

        switch (ordenation) {
            case 'L':
                batchList = productLocationDto.getBatchStock().stream().sorted(Comparator.comparingLong(Batch::getId)).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            case 'C':
                batchList = productLocationDto.getBatchStock().stream().sorted(Comparator.comparingInt(Batch::getStock)).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            case 'F':
                batchList = productLocationDto.getBatchStock().stream().sorted((b1, b2) -> String.CASE_INSENSITIVE_ORDER.compare(b1.getDueDate().toString(), b2.getDueDate().toString())).collect(Collectors.toList());

                productLocationDto.setBatchStock(batchList);

                return productLocationDto;

            default:
                throw new BusinessException("Parâmetro inválido");

        }
    }
}
