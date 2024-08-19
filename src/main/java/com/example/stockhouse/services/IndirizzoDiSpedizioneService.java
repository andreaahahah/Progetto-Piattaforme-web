package com.example.stockhouse.services;

import com.example.stockhouse.entities.IndirizzoDiSpedizione;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.repositories.IndirizzoDiSpedizioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoDiSpedizioneService {

    private IndirizzoDiSpedizioneRepository indirizzoDiSpedizioneRepository;

    public List<IndirizzoDiSpedizione> findIndirizzi(Utente utente){
        return indirizzoDiSpedizioneRepository.findByIdUtente(utente);
    }
}
