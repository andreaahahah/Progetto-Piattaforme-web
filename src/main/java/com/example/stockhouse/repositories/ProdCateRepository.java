package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.entities.ProdCate;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdCateRepository extends JpaRepository<ProdCate, Integer> {

    List<Prodotto>findProdCatesByIdCategoria(CategoriaProdotto c);
}
