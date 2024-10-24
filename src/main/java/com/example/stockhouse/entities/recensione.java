package com.example.stockhouse.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class recensione {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prodotto", nullable = false)
    private prodotto idProdotto;

    @Basic
    @Column(name = "valutazione", nullable = false)
    private Integer valutazione;

    @Basic
    @Column(name = "data", nullable = true)
    private Date data;

    @Basic
    @Column(name = "commento", nullable = true, length = -1)
    private String commento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utente", nullable = false)
    private utente idUtente;


}
