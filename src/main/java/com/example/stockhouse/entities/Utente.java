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
public class Utente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "id carrello", nullable = true)
    private Carrello idCarrello;

    @Basic
    @Column(name = "nome", nullable = true, length = -1)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = true, length = -1)
    private String cognome;

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;

    @OneToMany(targetEntity = Ordine.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Ordine> ordineLIst;

    @OneToMany(targetEntity = IndirizzoDiSpedizione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<IndirizzoDiSpedizione> indirizzoDiSpedizioneList;

    @OneToMany(targetEntity = Recensione.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Recensione>recensioneList;

    @OneToMany(targetEntity = DatiDiPagamento.class, mappedBy = "idUtente", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<DatiDiPagamento>datiDiPagamentoList;
}
