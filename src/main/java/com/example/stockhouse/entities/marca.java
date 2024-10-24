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
public class marca {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "nome", nullable = false, length = -1)
    private String nome;

    @OneToMany (targetEntity = prodotto.class, mappedBy = "marca", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<prodotto> prodotti;
}
