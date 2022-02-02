package com.w4.projetoIntegrador.dtos;

import com.w4.projetoIntegrador.entities.ItemCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    @NotNull
    private LocalDate date;

    @NotNull
    private Long buyerId;

    private String statusCode;

    private List<ItemCart> products;

}
