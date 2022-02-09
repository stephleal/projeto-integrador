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
    +  "batches.due_date as dueDate, batches.stock as quantity, "
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

    @Query(value = "SELECT DISTINCT batches.id as batchNumber, batches.product_announcement_id as productId, "
            + "products.product_type as productTypeId, batches.due_date as dueDate, batches.stock as quantity, "
            + "sections.id as sectionId  FROM batches, sections, inbounds, products "
            + "WHERE inbounds.section_id = sections.id AND batches.inbound_id = inbounds.id AND products.product_type = :productTypeId "
            + "ORDER BY dueDate ASC", nativeQuery = true)
    List<ValidDueDateProductsByCategory> findValidDueDateProductsByCategoryAsc(Integer productTypeId);


    @Query(value = "SELECT DISTINCT batches.id as batchNumber, batches.product_announcement_id as productId, "
            + "products.product_type as productTypeId, batches.due_date as dueDate, batches.stock as quantity, "
            + "sections.id as sectionId  FROM batches, sections, inbounds, products "
            + "WHERE inbounds.section_id = sections.id AND batches.inbound_id = inbounds.id AND products.product_type = :productTypeId "
            + "ORDER BY dueDate DESC", nativeQuery = true)
    List<ValidDueDateProductsByCategory> findValidDueDateProductsByCategoryDesc(Integer productTypeId);

    public interface ValidDueDateProductsByCategory{
        Long getBatchNumber();
        Long getProductId();
        Long getProductTypeId();
        LocalDate getDueDate();
        Integer getQuantity();
    }
}
