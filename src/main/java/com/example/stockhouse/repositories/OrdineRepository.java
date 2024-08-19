package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    DatiDiPagamento findIdPagamentoByIdOrdine(int idOrdine);
    List<Ordine> findOrdinesByData (Date data);

}
