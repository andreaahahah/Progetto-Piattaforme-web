package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Dati_di_pagamento;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatiDiPagamentoRepository extends JpaRepository<Dati_di_pagamento, Integer> {

    List<Dati_di_pagamento> findByIdUtente(Utente idUtente);

    Dati_di_pagamento findByIdUtenteAndNumeroCarta(Utente idUtente, String numeroCarta);

    Dati_di_pagamento findByIdUtenteAndAndIdPagamento(Utente idUtente, int id);
}
