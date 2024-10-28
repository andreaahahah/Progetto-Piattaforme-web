package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    Carrello findCarrelloByUtente(Utente u);



}
