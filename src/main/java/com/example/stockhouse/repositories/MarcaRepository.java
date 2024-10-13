package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    List<Marca> findMarcaByNomeContaining(String nome);

    Marca findMarcaByNome(String nome);
    List<Marca> findAllByOrderByNomeAsc();
}
