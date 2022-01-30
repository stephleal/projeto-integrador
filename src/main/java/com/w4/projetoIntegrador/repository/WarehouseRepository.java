package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
