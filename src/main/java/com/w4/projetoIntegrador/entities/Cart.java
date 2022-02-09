package com.w4.projetoIntegrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.w4.projetoIntegrador.enums.StatusCodeTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private LocalDate date;

    @Transient
    private Long buyerId;

    @Enumerated(EnumType.STRING)
    private StatusCodeTypes statusCode;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemCart> itemCarts;

    @ManyToOne
    @JsonIgnore
    private Buyer buyer;

}
