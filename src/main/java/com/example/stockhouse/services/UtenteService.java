package com.example.stockhouse.services;

import com.example.stockhouse.entities.utente;
import com.example.stockhouse.exceptions.UtenteAlreadyExist;
import com.example.stockhouse.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final CarrelloService carrelloService;
    public UtenteService(UtenteRepository utenteRepository,CarrelloService carrelloService) {
        this.utenteRepository = utenteRepository;
        this.carrelloService = carrelloService;
    }


    public utente findUtente(String email){
        return utenteRepository.findByEmail(email);
    }

    public void createUtente(String nome, String cognome, String email) throws UtenteAlreadyExist {
        if(utenteRepository.findByEmail(email)==null){
            utente utente = new utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setCarrello(carrelloService.createCarrello(utente));//TODO modifica il db
            utenteRepository.save(utente);
        }else{
            throw new UtenteAlreadyExist();
        }
    }

}
