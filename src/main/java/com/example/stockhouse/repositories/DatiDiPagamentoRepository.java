package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatiDiPagamentoRepository extends JpaRepository<DatiDiPagamento, Integer> {

    List<DatiDiPagamento> findByIdUtente(Utente idUtente);

    DatiDiPagamento findByIdUtenteAndNumeroCarta(Utente idUtente, String numeroCarta);

}
