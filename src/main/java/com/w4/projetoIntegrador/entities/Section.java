package com.w4.projetoIntegrador.entities;

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
@Entity(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProductTypes type;
    private float totalSpace;

    @ManyToOne
    private Warehouse warehouse;
}
