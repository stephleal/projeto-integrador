package com.w4.projetoIntegrador.dtos;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Section;
import java.time.LocalDateTime;
import java.util.List;

public class InboundDto {
    private Long orderNumber;
    private LocalDateTime orderDate;


    private Section section;

    private List<Batch> batchStock;
}
