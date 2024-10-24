package com.example.stockhouse.services;

import com.example.stockhouse.entities.*;
import com.example.stockhouse.exceptions.ProdottoNotAvaible;
import com.example.stockhouse.repositories.OrdineRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;

    public OrdineService(OrdineRepository ordineRepository, ProdottoRepository prodottoRepository) {
        this.ordineRepository = ordineRepository;
        this.prodottoRepository = prodottoRepository;
    }

    public dati_di_pagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<ordine> findOrdini(Date data){

        return ordineRepository.findOrdinesByData(data);
    }

    public void createOrdine(utente utente, carrello carrello, indirizzo_di_spedizione indirizzodispedizione, dati_di_pagamento datidipagamento) throws ProdottoNotAvaible {
        //TODO VERIFICARE SE IL CARRELLO HA PRODOTTI CON QUANTITA' POSITIVA

        ordine ordine = new ordine();
        ordine.setIdUtente(utente);
        //ordine.setIdCarrello(carrello);
        ordine.setIdIndirizzo(indirizzodispedizione);
        ordine.setIdPagamento(datidipagamento);

        HashMap<Integer,Integer> prodotti = new HashMap<>();
        for(dettaglio_carrello dc: carrello.getDettagliocarrelloList()){
            prodotti.put(dc.getIdProdotto().getId(),dc.getQuantità());
        }
        List<prodotto> prodLock = prodottoRepository.findProdottosByIdIn(prodotti.keySet());
        for(prodotto p: prodLock){
            if(prodottoRepository.existsByIdAndQuantitaGreaterThan(p.getId(),p.getQuantita())){
                p.setQuantita(p.getQuantita()- prodotti.get(p.getId()));}
            else{
                throw new ProdottoNotAvaible();
            }
        }
        prodottoRepository.saveAll(prodLock);
        ordine.setDettagliCarrello(carrello.getDettagliocarrelloList());// TODO è giusto?
        ordineRepository.save(ordine);

    }

}
