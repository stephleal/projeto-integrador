package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Null
    private Long id;

    @ManyToOne
    private ProductAnnouncement productAnnouncement;

    @JsonIgnore
    private CartDto cartDto;

    @NotNull
    private Integer quantity;

    @Transient
    private Long productAnnouncementId;
//
//    @Transient
//    private Long purchaseOrderId;

}
