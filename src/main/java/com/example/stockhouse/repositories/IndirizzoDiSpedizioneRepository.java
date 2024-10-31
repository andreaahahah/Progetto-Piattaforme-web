package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Indirizzo_di_spedizione;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizzoDiSpedizioneRepository extends JpaRepository<Indirizzo_di_spedizione, Integer> {
    List<Indirizzo_di_spedizione> findByIdUtente(Utente utente);
    Indirizzo_di_spedizione findByIdUtenteAndVia(Utente utente, String via);

    Indirizzo_di_spedizione findByIdUtenteAndId(Utente idUtente, int id);

}
