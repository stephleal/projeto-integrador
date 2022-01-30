package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProductTypes type;
    private float totalSpace;

    @ManyToOne
    @JsonIgnore
    private Warehouse warehouse;

    @Transient
    private Long warehouseId;

}
