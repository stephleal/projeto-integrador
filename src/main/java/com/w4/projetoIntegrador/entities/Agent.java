package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.hibernate.validator.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @NotEmpty(message = "Nome não pode ser nulo ou vazio")
    private String name;

    @ManyToOne
    @JsonIgnore
    private Section section;

    @Transient
    @NotNull(message = "sectionId não pode ser nulo")
    private Long sectionId;
}
