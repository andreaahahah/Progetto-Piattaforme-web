package com.example.stockhouse.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ProdCate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id prodcate", nullable = false)
    private int idProdcate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id prodotto", nullable = true)
    private Prodotto idProdotto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id categoria", nullable = true)
    private CategoriaProdotto idCategoria;





}
