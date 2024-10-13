package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.CategoriaProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<CategoriaProdotto, Integer> {

    List<CategoriaProdotto> findByNomeContaining(String nome);

    CategoriaProdottoRepository findCategoriaProdottoByNome(String nome);

    //metto in ordine le categorie in base al nome
    List<CategoriaProdotto> findAllByOrderByNomeAsc();


}
