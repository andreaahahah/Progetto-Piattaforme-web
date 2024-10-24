package com.example.stockhouse.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="ordine")
public class ordine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ordine", nullable = false)
    private int idOrdine;

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    private Date data;

    @Basic
    @Column(name = "totale", nullable = false, length = -1)
    private int totale;

    @OneToMany(mappedBy = "ordine")
    private List<dettaglio_carrello> dettagliCarrello;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_indirizzo", nullable = false)
    private indirizzo_di_spedizione idIndirizzo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utente", nullable = false)
    private utente idUtente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pagamento", nullable = false)
    private dati_di_pagamento idPagamento;


}
