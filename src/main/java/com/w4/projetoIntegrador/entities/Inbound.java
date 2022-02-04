package com.w4.projetoIntegrador.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Long id;

    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @NotNull
    private Section section;

    @ManyToOne
    private Agent agent;

    @OneToMany(mappedBy = "inbound", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull
    private List<Batch> batchList;
}
