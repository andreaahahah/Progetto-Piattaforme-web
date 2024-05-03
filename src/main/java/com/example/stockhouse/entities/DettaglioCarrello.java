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
@Table(name = "Dettaglio Carrello", schema = "public", catalog = "postgres")
public class DettaglioCarrello {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id dettaglio", nullable = false)
    private int idDettaglio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id carrello", nullable = true)
    private Carrello idCarrello;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id prodotto", nullable = true)
    private Prodotto idProdotto;

    @Basic
    @Column(name = "qunatità", nullable = true)
    private Integer qunatità;


}
