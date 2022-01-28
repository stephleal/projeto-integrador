package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAnnouncementRepository extends JpaRepository<ProductAnnouncement, Long> {
}
