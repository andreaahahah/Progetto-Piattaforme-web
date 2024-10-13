package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.entities.ProdCate;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdCateRepository extends JpaRepository<ProdCate, Integer> {


    @Query("SELECT pc.idProdotto " +
            "FROM ProdCate as pc" +
            " WHERE pc.idCategoria = ?1")
    List<Prodotto> findProdottosByIdCategoria(CategoriaProdotto c); //forse è meglio farsi passare direttamente l'id della categoria
}
