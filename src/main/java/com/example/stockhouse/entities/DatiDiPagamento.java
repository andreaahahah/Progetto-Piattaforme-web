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
@Table(name = "dati di pagamento", schema = "public", catalog = "postgres")
public class DatiDiPagamento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id pagamento", nullable = false)
    private int idPagamento;

    @ManyToOne
    @JoinColumn(name = "id utente", nullable = false)
    private Utente idUtente;
    
    @Basic
    @Column(name = "data scadenza", nullable = false)
    private Date dataScadenza;

    @Basic
    @Column(name = "codice sicurezza", nullable = true, length = -1)
    private String codiceSicurezza;

    @Basic
    @Column(name = "tipo carta", nullable = false, length = -1)
    private String tipoCarta;

    @Basic
    @Column(name = "numero carta", nullable = false, length = -1)
    private String numeroCarta;

    @Basic
    @Column(name = "nome carta", nullable = false, length = -1)
    private String nomeCarta;


}
