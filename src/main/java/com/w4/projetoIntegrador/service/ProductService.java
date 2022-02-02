package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.ProductDto;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

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
}


