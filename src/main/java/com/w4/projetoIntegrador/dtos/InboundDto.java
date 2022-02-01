package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InboundDto {

    //@Null
    private Long id;

    @JsonAlias("orderDate")
    @NotNull
    private LocalDateTime date;

    @JsonIgnore
    private SectionDto sectionDto;

    @NotNull
    private Long sectionId;

    private List<BatchDto> batchList;

    public Inbound convert(InboundDto idto, List<Batch> batcList) {
       Inbound.builder().date(idto.getDate()).batchList(batcList).section()
    }
}
