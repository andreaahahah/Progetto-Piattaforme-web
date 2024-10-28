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
@Table(name = "dettaglio_carrello", schema = "public", catalog = "postgres")
public class Dettaglio_carrello {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dettaglio", nullable = false)
    private int idDettaglio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrello", nullable = false)
    private Carrello idCarrello;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prodotto", nullable = false)
    private Prodotto idProdotto;

    @Basic
    @Column(name = "qunatità", nullable = false)
    private Integer quantità;

    @Basic
    @Column(name= "prezzo",nullable = false)
    private Integer prezzo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="id_ordine")
    private Ordine ordine;


}