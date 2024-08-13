package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.IndirizzoDiSpedizione;
import com.example.stockhouse.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndirizzoDiSpedizioneRepository extends JpaRepository<IndirizzoDiSpedizione, Integer> {
    List<IndirizzoDiSpedizione> findByIdUtente(Utente idUtente);


}
