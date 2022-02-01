package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchDto {

    //@Null
    private Long id;

    //@NotNull
    private Integer initialQuantity;

    private Integer stock;
    
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotNull
    private LocalDateTime manufacturingDateTime;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Float currentTemperature;

    private ProductTypes type;

    @NotNull
    private Long productId;

    @JsonIgnore
    private ProductAnnouncement productAnnouncement;

    @JsonIgnore
    private InboundDto inboundDto;
}
