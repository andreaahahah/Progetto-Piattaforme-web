package com.example.stockhouse.services;

import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.UtenteAlreadyExist;
import com.example.stockhouse.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final CarrelloService carrelloService;
    public UtenteService(UtenteRepository utenteRepository,CarrelloService carrelloService) {
        this.utenteRepository = utenteRepository;
        this.carrelloService = carrelloService;
    }

    public Optional<Utente> findUtente(int id){
        return utenteRepository.findById(id);
    }
    public Utente findUtente(String email){
        return utenteRepository.findByEmail(email);
    }

    public void createUtente(String nome, String cognome, String email) throws UtenteAlreadyExist {
        if(utenteRepository.findByEmail(email)==null){
            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setCarrello(carrelloService.createCarrello(utente));
            utenteRepository.save(utente);
        }else{
            throw new UtenteAlreadyExist();
        }
    }

}
