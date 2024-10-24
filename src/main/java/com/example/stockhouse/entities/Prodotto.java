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
@Table(name="prodotto")
public class Prodotto {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "prezzo", nullable = false)
    private Integer prezzo;


    @Column(name = "immagini", nullable = true, length = -1)
    private String immagini;

    @Basic
    @Column(name = "quantità", nullable = false)
    private Integer quantita;

    @Basic
    @Column(name = "vetrina", nullable = true)
    @JsonIgnore
    private boolean vetrina;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_marca")//non so se devo metterlo nel db
    private com.example.stockhouse.entities.marca marca;

    @Basic
    @Column(name = "nome", nullable = false, length = -1)
    private String nome;

    @Basic
    @Column(name = "descrizione", nullable = false, length = -1)
    private String descrizione;

    @OneToMany(targetEntity = recensione.class, mappedBy = "idProdotto", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<recensione> recensioni;
}
