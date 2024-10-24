package com.example.stockhouse.services;

import com.example.stockhouse.entities.carrello;
import com.example.stockhouse.entities.dettaglio_carrello;
import com.example.stockhouse.entities.utente;
import com.example.stockhouse.repositories.CarrelloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarrelloService {

    private final CarrelloRepository carrelloRepository;

    public CarrelloService(CarrelloRepository carrelloRepository) {
        this.carrelloRepository = carrelloRepository;
    }

    public carrello getCarrelloByUtente(utente utente)
    {
        if(carrelloRepository.findCarrelloByUtente(utente)==null){
            carrello carrello = new carrello();
            carrello.setUtente(utente);
            return carrelloRepository.save(carrello);
        }else {
            return carrelloRepository.findCarrelloByUtente(utente);
        }

    }

    @Transactional(readOnly = true)
    public List<dettaglio_carrello> getDettagliCarrello(Integer idCarrello) {
        carrello carrello = carrelloRepository.findById(idCarrello)
                .orElseThrow(() -> new IllegalArgumentException("Carrello not found with ID: " + idCarrello));
        return carrello.getDettagliocarrelloList();
    }


    public carrello createCarrello(utente utente) {
        carrello c = carrelloRepository.findCarrelloByUtente(utente);
        if(c ==null){
            carrello carrello = new carrello();
            carrello.setUtente(utente);
            carrelloRepository.save(carrello);
            return carrello;

        }else{
            return c;
        }
    }
}
