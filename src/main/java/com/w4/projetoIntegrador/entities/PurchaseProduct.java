package com.w4.projetoIntegrador.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purchases_products")
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @ManyToOne
    private ProductAnnouncement productAnnouncement;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @NotNull
    private Integer quantity;

    @Transient
    private Long productAnnouncementId;

    @Transient
    private Long purchaseOrderId;

}
