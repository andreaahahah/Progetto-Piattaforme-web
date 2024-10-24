package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.categoria_prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<categoria_prodotto, Integer> {

    List<categoria_prodotto> findByNomeContaining(String nome);

    CategoriaProdottoRepository findCategoriaProdottoByNome(String nome);

    //metto in ordine le categorie in base al nome
    List<categoria_prodotto> findAllByOrderByNomeAsc();


}
