package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAnnouncementDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String brand;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Float volume;

    @NotNull
    private Float minimumTemperature;

    @NotNull
    private Float maximumTemperature;

    @JsonIgnore
    private ProductDto productDto;

    @JsonIgnore
    private SellerDto sellerDto;

    @Transient
    @NotNull
    private Long productId;

    @Transient
    @NotNull
    private Long sellerId;
}
