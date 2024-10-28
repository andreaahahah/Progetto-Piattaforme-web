package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Categoria_prodotto;
import com.example.stockhouse.entities.Prod_cate;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdCateRepository extends JpaRepository<Prod_cate, Integer> {


    @Query("SELECT pc.idProdotto " +
            "FROM Prod_cate  pc" +
            " WHERE pc.idCategoria = ?1")
    List<Prodotto> findProdottosByIdCategoria(Categoria_prodotto c); //forse è meglio farsi passare direttamente l'id della categoria

    @Query("SELECT pc.idCategoria " +
            "FROM Prod_cate  pc" +
            " WHERE pc.idProdotto = ?1")
    List<Categoria_prodotto> findCategoriaByIdProdotto(Prodotto p);
}
