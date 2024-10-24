package com.example.stockhouse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name ="utente")
public class utente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_carrello", nullable = true)//da vedere
    private com.example.stockhouse.entities.carrello carrello;

    @Basic
    @Column(name = "nome", nullable = false, length = -1)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false, length = -1)
    private String cognome;

    @Basic
    @Column(name = "email", nullable = false, length = -1, unique = true)
    private String email;

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;

    @OneToMany(targetEntity = ordine.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<ordine> ordineLIst;

    @OneToMany(targetEntity = indirizzo_di_spedizione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<indirizzo_di_spedizione> indirizzodispedizioneList;

    @OneToMany(targetEntity = recensione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<recensione>recensioneList;

    @OneToMany(targetEntity = dati_di_pagamento.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<dati_di_pagamento> datidipagamentoList;
}
