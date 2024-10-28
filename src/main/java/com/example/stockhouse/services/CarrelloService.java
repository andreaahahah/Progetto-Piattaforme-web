package com.example.stockhouse.services;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.Dettaglio_carrello;
import com.example.stockhouse.entities.Utente;
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

    public Carrello getCarrelloByUtente(Utente utente)
    {
        if(carrelloRepository.findCarrelloByUtente(utente)==null){
            Carrello carrello = new Carrello();
            carrello.setUtente(utente);
            return carrelloRepository.save(carrello);
        }else {
            return carrelloRepository.findCarrelloByUtente(utente);
        }

    }

    @Transactional(readOnly = true)
    public List<Dettaglio_carrello> getDettagliCarrello(Integer idCarrello) {
        Carrello carrello = carrelloRepository.findById(idCarrello)
                .orElseThrow(() -> new IllegalArgumentException("Carrello not found with ID: " + idCarrello));
        return carrello.getDettagliocarrelloList();
    }


    public Carrello createCarrello(Utente utente) {
        Carrello c = carrelloRepository.findCarrelloByUtente(utente);
        if(c ==null){
            Carrello carrello = new Carrello();
            carrello.setUtente(utente);
            carrelloRepository.save(carrello);
            return carrello;

        }else{
            return c;
        }
    }
}
