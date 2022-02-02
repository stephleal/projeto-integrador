package com.w4.projetoIntegrador.repository;

import java.util.List;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
     List<Batch> findByProductAnnouncement(ProductAnnouncement product);

     @Query(value = "select  sum(stock) as stock, product_announcement_id from batches where product_announcement_id  = :id group by product_announcement_id", nativeQuery = true)
     List<SoldStock> getStock(@Param("id")Long id);
     public interface SoldStock{
          Integer getStock();
     }

}
