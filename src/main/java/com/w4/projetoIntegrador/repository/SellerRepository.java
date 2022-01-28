package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface SellerRepository extends JpaRepository<Seller, Long> {
    }

