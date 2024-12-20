package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.entities.Prodotto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    List<Prodotto> findProdottosByPrezzoBetween(int prezzoMin, int prezzoMax);
    boolean existsByIdAndQuantitaGreaterThan(int id, int quantita);

    List<Prodotto> findProdottosByMarca(Marca marca);

    List<Prodotto> findProdottosByNome(String nome);

    Prodotto findByNomeAndDescrizioneAndMarca(String nome, String descrizione, Marca marca);


    @Query("SELECT p " +
            "FROM Prodotto as p" +
            " WHERE p.quantita > 0")
    List<Prodotto> findProdottosWithPositiveQuantita();




    @Query("SELECT p " +
            "FROM Prodotto as p "+
            "WHERE (p.nome LIKE %?1% OR ?1 IS NULL) OR " +
            "(p.descrizione LIKE %?1% OR ?1 IS NULL)")
    List<Prodotto> advancedSearch(String name);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Prodotto> findProdottosByIdIn(Set<Integer> prodotti);


    Prodotto findProdottoById(int id);

    List<Prodotto>findProdottosByVetrinaIsTrueAndQuantitaGreaterThan(int quantità);

    boolean existsById(int id);



}