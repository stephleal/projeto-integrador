package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.ItemCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {
}
