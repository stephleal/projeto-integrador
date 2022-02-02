package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.entities.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboundDto {

    private Long orderNumber;

    @JsonAlias("orderDate")
    private LocalDateTime date;

    @NotNull
    private Long sectionId;

    @NotNull
    private Long agentId;

    @Valid
    @NotNull
    @JsonAlias("batchList")
    private List<BatchDto> batchDtoList;

    public static Inbound convert(InboundDto idto, List<Batch> batcList, Section s) {
       Inbound inbound = Inbound.builder().date(idto.getDate()).batchList(batcList).section(s).build();
       return inbound;
    }
}
