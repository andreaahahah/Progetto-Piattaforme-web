package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.entities.recensione;
import com.example.stockhouse.entities.utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecensioneRepository extends JpaRepository<recensione, Integer> {
    List<recensione> findRecensioneByIdUtente(utente idUtente);

    List<recensione> findRecensioneByIdProdotto(Prodotto idProdotto);

    recensione findRecensioneByIdUtenteAndAndIdProdotto(utente idUtente, Prodotto idProdotto);
}
