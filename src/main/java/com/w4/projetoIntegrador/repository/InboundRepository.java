package com.w4.projetoIntegrador.repository;

import com.w4.projetoIntegrador.entities.Inbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundRepository extends JpaRepository<Inbound, Long> {

    @Query(value = "select s.id, sum(p.volume * b.stock) "
            + "as volume from sections s inner join inbounds i on "
            + "i.section_id = s.id inner join batches b on b.inbound_id = i.id "
            + "inner join products_announcements p on p.id = b.product_announcement_id group by s.id", nativeQuery = true)
    List<SectionsCapacity> findCapacityAllSections();

    public interface SectionsCapacity{
        Long getId();
        Float getVolume();
    }
}
