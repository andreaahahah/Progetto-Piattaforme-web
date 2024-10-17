package com.example.stockhouse.services;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.DettaglioCarrello;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.repositories.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Transactional(readOnly = true)
    public Carrello getCarrelloByUtente(Utente utente) {
        return carrelloRepository.findCarrelloByUtente(utente);
    }

    @Transactional(readOnly = true)
    public List<DettaglioCarrello> getDettagliCarrello(Integer idCarrello) {
        Carrello carrello = carrelloRepository.findById(idCarrello)
                .orElseThrow(() -> new IllegalArgumentException("Carrello not found with ID: " + idCarrello));
        return carrelloRepository.findDettagli(carrello);
    }

    //TODO  come devo crearlo? lo screo e faccio il setIdUtente oppure si collega in automatico? mhhh
    public void createCarrello(){

    }

}
