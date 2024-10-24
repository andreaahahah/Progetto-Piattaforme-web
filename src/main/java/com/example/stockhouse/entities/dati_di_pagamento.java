package com.example.stockhouse.entities;

import jakarta.persistence.*;

import java.sql.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "dati_di_pagamento", schema = "public", catalog = "postgres")
public class dati_di_pagamento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pagamento", nullable = false)
    private int idPagamento;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private utente idUtente;
    
    @Basic
    @Column(name = "data_scadenza", nullable = false)
    private Date dataScadenza;

    @Basic
    @Column(name = "codice_sicurezza", nullable = true, length = -1)
    private String codiceSicurezza;

    @Basic
    @Column(name = "tipo_carta", nullable = false, length = -1)
    private String tipoCarta;

    @Basic
    @Column(name = "numero_carta", nullable = false, length = -1)
    private String numeroCarta;

    @Basic
    @Column(name = "nome_carta", nullable = false, length = -1)
    private String nomeCarta;


}
