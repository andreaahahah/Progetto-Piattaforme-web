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
@Table(name="prod_cate")
public class prod_cate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_prodcate", nullable = false)
    private int idProdcate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prodotto", nullable = true)
    private Prodotto idProdotto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria", nullable = true)
    private categoria_prodotto idCategoria;





}
