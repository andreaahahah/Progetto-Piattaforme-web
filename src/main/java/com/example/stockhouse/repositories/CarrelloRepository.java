package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.DettaglioCarrello;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    Carrello findCarrelloById_utente(Utente u);

    @Query("SELECT c.dettaglioCarrelloList " +
            "FROM Carrello c "+
            "WHERE c.idCarrello = ?1 "
    )
    List<DettaglioCarrello>findDettagli(Carrello c);



}
