package com.example.stockhouse.services;

import com.example.stockhouse.entities.*;
import com.example.stockhouse.exceptions.ProdottoNotAvaible;
import com.example.stockhouse.repositories.OrdineRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Dati_di_pagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<Ordine> findOrdini(Date data){

        return ordineRepository.findOrdinesByData(data);
    }

    @Transactional
    public void createOrdine(Utente utente, List<Dettaglio_carrello>DettagliocarrelloList, Indirizzo_di_spedizione indirizzodispedizione, Dati_di_pagamento datidipagamento) throws ProdottoNotAvaible {

        int totale = 0;
        Ordine ordine = new Ordine();
        ordine.setIdUtente(utente);
        ordine.setIdIndirizzo(indirizzodispedizione);
        ordine.setIdPagamento(datidipagamento);

        HashMap<Integer,Integer> prodotti = new HashMap<>();
        for(Dettaglio_carrello dc: DettagliocarrelloList){
            prodotti.put(dc.getIdProdotto().getId(),dc.getQuantità());
        }
        List<Prodotto> prodLock = prodottoRepository.findProdottosByIdIn(prodotti.keySet());//prendo il lock

        for(Prodotto p: prodLock){
            if(p.getQuantita()>= prodotti.get(p.getId())){

                totale+=p.getPrezzo();
                p.setQuantita(p.getQuantita()- prodotti.get(p.getId()));}
            else{
                throw new ProdottoNotAvaible();
            }
        }
        prodottoRepository.saveAll(prodLock);
        ordine.setTotale(totale);
        ordine.setDettagliCarrello(DettagliocarrelloList);
        ordineRepository.save(ordine);

    }

    public List<Ordine> getOrdini(Utente utente){
        return ordineRepository.findOrdinesByIdUtente(utente);
    }

}
