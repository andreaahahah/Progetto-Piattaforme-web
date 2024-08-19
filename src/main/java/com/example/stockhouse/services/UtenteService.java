package com.example.stockhouse.services;

import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    private UtenteRepository utenteRepository;

    public Utente findUtente(String email){

        return utenteRepository.findByEmail(email);
    }

}
