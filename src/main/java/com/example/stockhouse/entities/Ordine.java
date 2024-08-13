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
public class Ordine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id ordine", nullable = false)
    private int idOrdine;

    @OneToOne
    @JoinColumn(name = "id carrello", nullable = true)
    private Carrello idCarrello;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id indirizzo", nullable = true)
    private IndirizzoDiSpedizione idIndirizzo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id utente", nullable = true)
    private Utente idUtente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id pagamento", nullable = true)
    private DatiDiPagamento idPagamento;

//potresti aggiungere la data e il totale
}
