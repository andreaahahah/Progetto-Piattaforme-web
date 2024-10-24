package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.dati_di_pagamento;
import com.example.stockhouse.entities.utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatiDiPagamentoRepository extends JpaRepository<dati_di_pagamento, Integer> {

    List<dati_di_pagamento> findByIdUtente(utente idUtente);

    dati_di_pagamento findByIdUtenteAndNumeroCarta(utente idUtente, String numeroCarta);

}
