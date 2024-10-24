package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.carrello;
import com.example.stockhouse.entities.dettaglio_carrello;
import com.example.stockhouse.entities.utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarrelloRepository extends JpaRepository<carrello, Integer> {

    carrello findCarrelloByUtente(utente u);



}
