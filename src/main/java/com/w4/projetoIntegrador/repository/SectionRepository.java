package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
