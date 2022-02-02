package com.w4.projetoIntegrador.dtos;

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

    private Long id;

    @NotNull
    private LocalDate date;

    @Transient
    //@NotNull
    private Long buyerId;

    private String statusCode;

    private List<ItemCartDto> itemCartDtos;

    private BuyerDto buyer;

}
