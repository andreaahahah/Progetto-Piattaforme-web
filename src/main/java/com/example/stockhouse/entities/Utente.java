package com.example.stockhouse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Utente {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "id_carrello", nullable = true)//da vedere
    private Carrello carrello;

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

    @OneToMany(targetEntity = Ordine.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Ordine> ordineLIst;

    @OneToMany(targetEntity = Indirizzo_di_spedizione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Indirizzo_di_spedizione> indirizzodispedizioneList;

    @OneToMany(targetEntity = Recensione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Recensione>recensioneList;

    @OneToMany(targetEntity = Dati_di_pagamento.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Dati_di_pagamento> datidipagamentoList;
}
