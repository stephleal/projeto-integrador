package com.w4.projetoIntegrador.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @Transient
    //@NotNull
    private Long buyerId;

    private String statusCode;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemCart> itemCarts;

    @ManyToOne
    private Buyer buyer;

}