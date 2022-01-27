package com.w4.projetoIntegrador.dtos;

import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.entities.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundDto {
    private Long orderNumber;
    private LocalDateTime orderDate;


    private Section section;

    private List<Batch> batchStock;

    public static Inbound convert(InboundDto dto) {
        Inbound inbound = Inbound.builder()
                .id(dto.getOrderNumber())
                .date(dto.getOrderDate())
                .section(dto.getSection())
                .batchList(dto.getBatchStock())
                .build();

        return inbound;
    }
}
