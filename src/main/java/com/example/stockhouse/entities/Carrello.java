package com.example.stockhouse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Carrello {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id carrello", nullable = false)
    private int idCarrello;

    @Basic
    @Column(name = "data creazione", nullable = true)
    private Date dataCreazione;

    @Basic
    @Column(name = "totale carrello", nullable = true, length = -1)
    private int totale_carrello;

    @OneToOne(mappedBy = "carrello")
    private Utente utente;

    @OneToMany(targetEntity = DettaglioCarrello.class, mappedBy = "idCarrello", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<DettaglioCarrello> dettaglioCarrelloList;

}
