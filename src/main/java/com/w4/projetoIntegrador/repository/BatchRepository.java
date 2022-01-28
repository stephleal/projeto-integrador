package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
}
