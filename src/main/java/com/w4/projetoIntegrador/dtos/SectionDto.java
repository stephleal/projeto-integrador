package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDto {

    private Long id;

    @NotNull
    private ProductTypes type;

    @NotNull
    private float totalSpace;

    @JsonIgnore
    private WarehouseDto warehouseDto;

    @Transient
    @NotNull
    private Long warehouseId;
}
