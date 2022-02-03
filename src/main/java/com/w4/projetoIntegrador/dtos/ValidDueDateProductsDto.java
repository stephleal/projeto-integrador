package com.w4.projetoIntegrador.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidDueDateProductsDto {
    Long batchNumber;
    Long productId;
    Long productTypeId;
    LocalDate dueDate;
    Integer quantity;
}
