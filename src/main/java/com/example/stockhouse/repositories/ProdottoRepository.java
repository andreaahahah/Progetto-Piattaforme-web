package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    List<Prodotto> findProdottosByPrezzoBetween(int prezzoMin, int prezzoMax);
    boolean existsByIdAndQuantityGreaterThan(Long id, int quantity);

    List<Prodotto> findProdottosByMarca(String m);

    List<Prodotto> findProdottosByNome(String nome);


    @Query("SELECT p " +
            "FROM Prodotto p" +
            " WHERE p.quantità > 0")
    List<Prodotto> findProdottosWithPositiveQuantity();



    @Query("SELECT p " +
            "FROM Prodotto p "+
            "WHERE (p.nome LIKE ?1 OR ?1 IS NULL) AND " +
            "(p.descrizione LIKE ?2 OR ?2 IS NULL)")
    List<Prodotto> advancedSearch(String name, String description);



}