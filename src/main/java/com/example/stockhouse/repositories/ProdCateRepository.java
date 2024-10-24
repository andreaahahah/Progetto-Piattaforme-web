package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.categoria_prodotto;
import com.example.stockhouse.entities.prod_cate;
import com.example.stockhouse.entities.prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdCateRepository extends JpaRepository<prod_cate, Integer> {


    @Query("SELECT pc.idProdotto " +
            "FROM prod_cate as pc" +
            " WHERE pc.idCategoria = ?1")
    List<prodotto> findProdottosByIdCategoria(categoria_prodotto c); //forse è meglio farsi passare direttamente l'id della categoria

    @Query("SELECT pc.idCategoria " +
            "FROM prod_cate as pc" +
            " WHERE pc.idProdotto = ?1")
    List<categoria_prodotto> findCategoriaByIdProdotto(prodotto p);
}
