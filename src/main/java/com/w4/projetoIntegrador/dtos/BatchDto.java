package com.w4.projetoIntegrador.dtos;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Product;
import com.w4.projetoIntegrador.entities.ProductAnnouncement;
import com.w4.projetoIntegrador.enums.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchDto {
    private Integer batchNumber;
    private String productId;
    private Float currentTemperature;
    private Float minimumTemperature;
    private Float maximumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDateTime manufacturingDate;
    private LocalDate dueDate;
    private ProductTypes type;
    private Integer product_id;

}
