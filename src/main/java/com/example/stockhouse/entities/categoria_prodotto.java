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
@Table(name = "categoria_prodotto", schema = "public", catalog = "postgres")
public class categoria_prodotto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id_categoria;

    @Basic
    @Column(name = "nome", nullable = true, length = -1)
    private String nome;

    @Basic
    @Column(name = "descrizione", nullable = true, length = -1)
    private String descrizione;


}
