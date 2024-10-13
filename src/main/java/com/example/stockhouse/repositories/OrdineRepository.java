package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.IndirizzoDiSpedizione;
import com.example.stockhouse.entities.Ordine;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    DatiDiPagamento findIdPagamentoByIdOrdine(int idOrdine);
    List<Ordine> findOrdinesByData (Date data);

    List<Ordine> findOrdinesByIdIndirizzo(IndirizzoDiSpedizione idIndirizzo);

    List<Ordine> findOrdinesByIdPagamento(DatiDiPagamento DatiDiPagamento);
}
