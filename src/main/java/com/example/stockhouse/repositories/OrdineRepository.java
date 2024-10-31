package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Dati_di_pagamento;
import com.example.stockhouse.entities.Indirizzo_di_spedizione;
import com.example.stockhouse.entities.Ordine;
import com.example.stockhouse.entities.Utente;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    Dati_di_pagamento findIdPagamentoByIdOrdine(int idOrdine);
    List<Ordine> findOrdinesByData (Date data);

    List<Ordine> findOrdinesByIdIndirizzo(Indirizzo_di_spedizione idIndirizzo);

    List<Ordine> findOrdinesByIdPagamento(Dati_di_pagamento dati_di_pagamento);

    List<Ordine>findOrdinesByIdUtente(Utente utente);
}
