package com.w4.projetoIntegrador.entities;

import com.w4.projetoIntegrador.enums.ProductTypes;

import java.time.LocalDateTime;
import java.util.Date;

public class Batch {
    private Long id;
    private Integer initialQuantity;
    private Integer stock;
    private LocalDateTime manufacturingDateTime;
    private Date dueDate;
    private Float currentTemperature;
    private ProductTypes type;
    private ProductAnnouncement productAnnouncement;

}
