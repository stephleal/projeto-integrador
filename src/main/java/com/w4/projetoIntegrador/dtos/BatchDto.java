package com.w4.projetoIntegrador.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    
}
