package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.marca;
import com.example.stockhouse.entities.prodotto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdottoRepository extends JpaRepository<prodotto, Integer> {

    List<prodotto> findProdottosByPrezzoBetween(int prezzoMin, int prezzoMax);
    boolean existsByIdAndQuantitaGreaterThan(int id, int quantita);

    List<prodotto> findProdottosByMarca(marca marca);

    List<prodotto> findProdottosByNome(String nome);

    prodotto findByNomeAndDescrizioneAndMarca(String nome, String descrizione, marca marca);


    @Query("SELECT p " +
            "FROM prodotto as p" +
            " WHERE p.quantita > 0")
    List<prodotto> findProdottosWithPositiveQuantita();



    @Query("SELECT p " +
            "FROM prodotto as p "+
            "WHERE (p.nome LIKE ?1 OR ?1 IS NULL) AND " +
            "(p.descrizione LIKE ?2 OR ?2 IS NULL)")
    List<prodotto> advancedSearch(String name, String description);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<prodotto> findProdottosByIdIn(Set<Integer> prodotti);


    prodotto findProdottoById(int id);

    List<prodotto>findProdottosByVetrinaIsTrue();

    boolean existsById(int id);



}