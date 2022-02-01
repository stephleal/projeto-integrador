package com.w4.projetoIntegrador.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentDto {

    @Null
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @JsonIgnore
    private SectionDto sectionDto;

    @NotNull
    private Long sectionId;
}
