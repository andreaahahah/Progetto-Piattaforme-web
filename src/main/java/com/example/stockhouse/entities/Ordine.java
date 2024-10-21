package com.example.stockhouse.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.Data;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Ordine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id ordine", nullable = false)
    private int idOrdine;

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    private Date data;

    @Basic
    @Column(name = "totale", nullable = false, length = -1)
    private int totale;

    @OneToOne
    @JoinColumn(name = "id carrello", nullable = false)
    private Carrello idCarrello;
    //TODO metti una lista di dettagli carrello al suo posto

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id indirizzo", nullable = false)
    private IndirizzoDiSpedizione idIndirizzo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id utente", nullable = false)
    private Utente idUtente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id pagamento", nullable = false)
    private DatiDiPagamento idPagamento;


}
