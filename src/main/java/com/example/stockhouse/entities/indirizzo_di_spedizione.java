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
@Table(name = "indirizzo_di_spedizione", schema = "public", catalog = "postgres")
public class indirizzo_di_spedizione {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utente", nullable = false)
    private utente idUtente;

    @Basic
    @Column(name = "via", nullable = false, length = -1)
    private String via;

    @Basic
    @Column(name = "città", nullable = false, length = -1)
    private String città;

    @Basic
    @Column(name = "cap", nullable = false, length = -1)
    private String cap;

    @Basic
    @Column(name = "nazione", nullable = false, length = -1)
    private String nazione;

    @Basic
    @Column(name = "note", nullable = true, length = -1)
    private String note;


}
