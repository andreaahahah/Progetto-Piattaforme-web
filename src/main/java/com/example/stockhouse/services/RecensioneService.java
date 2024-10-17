package com.example.stockhouse.services;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.entities.Recensione;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.CategoriaProdottoAlreadyExist;
import com.example.stockhouse.exceptions.RecensioneAlreadyExist;
import com.example.stockhouse.repositories.RecensioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecensioneService {
    private RecensioneRepository recensioneRepository;

    @Transactional(readOnly = true)
    public List<Recensione> findRecensioneByUtente(Utente utente){
        return recensioneRepository.findRecensioneByIdUtente(utente);
    }

    @Transactional(readOnly = true)
    public List<Recensione> findRecensioneByProdotto(Prodotto prodotto){
        return recensioneRepository.findRecensioneByIdProdotto(prodotto);
    }
    public void createRecensione(Utente utente, Prodotto prodotto, int valutazione) throws RecensioneAlreadyExist {
        if(recensioneRepository.findRecensioneByIdUtenteAndAndIdProdotto(utente, prodotto) == null){
            Recensione recensione = new Recensione();
            recensione.setIdUtente(utente);
            recensione.setIdProdotto(prodotto);
            recensione.setValutazione(valutazione);
        }
        else{
            throw new RecensioneAlreadyExist();
        }
    }

    public void createRecensione(Utente utente, Prodotto prodotto, int valutazione, String commento) throws RecensioneAlreadyExist {
        if(recensioneRepository.findRecensioneByIdUtenteAndAndIdProdotto(utente, prodotto) == null){
            Recensione recensione = new Recensione();
            recensione.setIdUtente(utente);
            recensione.setIdProdotto(prodotto);
            recensione.setValutazione(valutazione);
            recensione.setCommento(commento);
        }
        else{
            throw new RecensioneAlreadyExist();
        }
    }
    // TODO GESTISCI LA DATA
}
