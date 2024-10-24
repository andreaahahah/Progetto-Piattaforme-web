package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<utente, Integer> {

    utente findByEmail(String email);

}
