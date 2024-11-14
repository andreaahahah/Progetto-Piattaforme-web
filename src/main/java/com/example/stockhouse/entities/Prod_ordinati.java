package com.example.stockhouse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="prod_ordinati")
public class Prod_ordinati {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id_ordinati", nullable = false)
    private int idOrdinati;

    @ManyToOne
    @JoinColumn(name ="id_prodotto")
    @JsonIgnore
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name ="id_ordine")
    @JsonIgnore
    private Ordine ordine;

    @Basic
    @Column(name = "quantita", nullable = false)
    private Integer quantita;

    @Basic
    @Column(name= "prezzo",nullable = false)
    private Integer prezzo;
}
