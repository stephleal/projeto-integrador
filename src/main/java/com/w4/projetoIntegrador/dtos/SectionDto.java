package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDto {


    @JsonAlias({"sectionCode"})
    private Long id;

    private String warehouseCode;
}
