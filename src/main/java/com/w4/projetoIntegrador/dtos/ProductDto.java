package com.w4.projetoIntegrador.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    //@Null
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private String productType;

}
