package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.PurchaseOrder;
import com.w4.projetoIntegrador.entities.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
}
