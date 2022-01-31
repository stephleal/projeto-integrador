package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inbounds")
public class Inbound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @JsonAlias("orderDate")
    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnore
    private Section section;

    @Transient
    @NotNull
    private Long sectionId;

    @OneToMany(mappedBy = "inbound", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Batch> batchList;
}
