package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.entities.Recensione;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {
    List<Recensione> findRecensioneByIdUtente(Utente idUtente);

    List<Recensione> findRecensioneByIdProdotto(Prodotto idProdotto);
}
