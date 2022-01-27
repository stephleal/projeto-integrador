package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "batches")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer initialQuantity;
    private Integer stock;
    private LocalDateTime manufacturingDateTime;
    private LocalDate dueDate;
    private Float currentTemperature;
    private ProductTypes type;

    @ManyToOne
    @JsonIgnore
    private ProductAnnouncement productAnnouncement;

    @Transient
    private Integer productId;

    @ManyToOne
    private Inbound inbound;
}
