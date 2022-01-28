package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products_announcements")
public class ProductAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Float volume;
    private Float minimumTemperature;
    private Float maximumTemperature;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Seller seller;

    @Transient
    private Long productId;

    @Transient
    private Long sellerId;
}
