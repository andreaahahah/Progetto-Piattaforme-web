package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.dati_di_pagamento;
import com.example.stockhouse.entities.indirizzo_di_spedizione;
import com.example.stockhouse.entities.ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<ordine, Integer> {

    dati_di_pagamento findIdPagamentoByIdOrdine(int idOrdine);
    List<ordine> findOrdinesByData (Date data);

    List<ordine> findOrdinesByIdIndirizzo(indirizzo_di_spedizione idIndirizzo);

    List<ordine> findOrdinesByIdPagamento(dati_di_pagamento dati_di_pagamento);
}
