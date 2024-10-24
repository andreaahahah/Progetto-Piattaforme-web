package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.indirizzo_di_spedizione;
import com.example.stockhouse.entities.utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizzoDiSpedizioneRepository extends JpaRepository<indirizzo_di_spedizione, Integer> {
    List<indirizzo_di_spedizione> findByIdUtente(utente utente);
    indirizzo_di_spedizione findByIdUtenteAndVia(utente utente, String via);


}
