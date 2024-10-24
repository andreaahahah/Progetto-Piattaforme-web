package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<marca, Integer> {

    List<marca> findMarcaByNomeContaining(String nome);

    marca findMarcaByNome(String nome);
    List<marca> findAllByOrderByNomeAsc();

    Boolean existsByNome(String nome);
}
