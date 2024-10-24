package com.example.stockhouse.services;

import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.entities.recensione;
import com.example.stockhouse.entities.utente;
import com.example.stockhouse.exceptions.RecensioneAlreadyExist;
import com.example.stockhouse.repositories.RecensioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecensioneService {
    private final RecensioneRepository recensioneRepository;

    public RecensioneService(RecensioneRepository recensioneRepository) {
        this.recensioneRepository = recensioneRepository;
    }

    @Transactional(readOnly = true)
    public List<recensione> findRecensioneByUtente(utente utente){
        return recensioneRepository.findRecensioneByIdUtente(utente);
    }

    @Transactional(readOnly = true)
    public List<recensione> findRecensioneByProdotto(Prodotto prodotto){
        return recensioneRepository.findRecensioneByIdProdotto(prodotto);
    }
    public void createRecensione(utente utente, Prodotto prodotto, int valutazione) throws RecensioneAlreadyExist {
        if(recensioneRepository.findRecensioneByIdUtenteAndAndIdProdotto(utente, prodotto) == null){
            recensione recensione = new recensione();
            recensione.setIdUtente(utente);
            recensione.setIdProdotto(prodotto);
            recensione.setValutazione(valutazione);
            recensioneRepository.save(recensione);
        }
        else{
            throw new RecensioneAlreadyExist();
        }
    }

    public void createRecensione(utente utente, Prodotto prodotto, int valutazione, String commento) throws RecensioneAlreadyExist {
        if(recensioneRepository.findRecensioneByIdUtenteAndAndIdProdotto(utente, prodotto) == null){
            recensione recensione = new recensione();
            recensione.setIdUtente(utente);
            recensione.setIdProdotto(prodotto);
            recensione.setValutazione(valutazione);
            recensione.setCommento(commento);
            recensioneRepository.save(recensione);
        }
        else{
            throw new RecensioneAlreadyExist();
        }
    }
    // TODO GESTISCI LA DATA
}
