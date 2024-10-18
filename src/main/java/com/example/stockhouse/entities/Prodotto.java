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
public class Prodotto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "prezzo", nullable = false)
    private Integer prezzo;


    @Column(name = "immagini", nullable = false, length = -1)
    private String immagini;

    @Basic
    @Column(name = "quantità", nullable = false)
    private Integer quantita;

    @Version
    @Basic
    @Column(name = "version", nullable = true)//TODO
    @JsonIgnore
    private Integer version;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id marca")//non so se devo metterlo nel db
    private Marca marca;

    @Basic
    @Column(name = "nome", nullable = false, length = -1)
    private String nome;

    @Basic
    @Column(name = "descrizione", nullable = false, length = -1)
    private String descrizione;

    @OneToMany(targetEntity = Recensione.class, mappedBy = "idProdotto", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Recensione> recensioni;
}
