package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w4.projetoIntegrador.entities.ItemCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCartDto {

    @NotNull
    private Integer quantity;

    @NotNull
    private Long productAnnouncementId;

    public static ItemCartDto convert(ItemCart itemCart){
        return ItemCartDto.builder()
                .quantity(itemCart.getQuantity())
                .productAnnouncementId(itemCart.getProductAnnouncement().getId())
                .build();
    }
}
