package com.w4.projetoIntegrador.repository;

import java.time.LocalDate;

import com.w4.projetoIntegrador.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    @Query(value = "select batches.id as batchNumber, batches.product_announcement_id as productId, "
    +  "batches.type as productTypeId, batches.due_date as dueDate, batches.stock as quantity, "
    + "sections.id as sectionId  from batches, sections, inbounds where inbounds.section_id = sections.id and "
    + "batches.inbound_id = inbounds.id and sections.id = :sectionId order by dueDate", nativeQuery = true)
    List<ValidDueDateProducts> findValidDueDateProducts(Long sectionId);

    public interface ValidDueDateProducts {
        Long getBatchNumber();
        Long getProductId();
        Long getProductTypeId();
        LocalDate getDueDate();
        Integer getQuantity();
    }



}
