package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Categoria_prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<Categoria_prodotto, Integer> {

    List<Categoria_prodotto> findByNomeContaining(String nome);

    Optional<Categoria_prodotto> findCategoriaProdottoByNome(String nome);

    //metto in ordine le categorie in base al nome
    List<Categoria_prodotto> findAllByOrderByNomeAsc();


}
