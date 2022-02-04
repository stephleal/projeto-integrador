package com.w4.projetoIntegrador.dtos;

import com.w4.projetoIntegrador.entities.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    public static BuyerDto convert(Buyer buyer){
        return BuyerDto.builder().id(buyer.getId()).name(buyer.getName()).build();
    }

    public static Buyer convert(BuyerDto buyerDto){
        return Buyer.builder().name(buyerDto.getName()).build();
    }
}
