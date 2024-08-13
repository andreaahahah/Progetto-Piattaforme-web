package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    DatiDiPagamento findIdPagamentoByIdOrdine(int idOrdine);

}
