package com.example.stockhouse.services;

import com.example.stockhouse.entities.*;
import com.example.stockhouse.exceptions.ProdottoNotAvaible;
import com.example.stockhouse.repositories.OrdineRepository;
import com.example.stockhouse.repositories.ProdOrdinatiRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;

    private final DettaglioCarrelloService dettaglioCarrelloService;
    private final ProdOrdinatiRepository prodOrdinatiRepository;

    public OrdineService(OrdineRepository ordineRepository, ProdottoRepository prodottoRepository, DettaglioCarrelloService dettaglioCarrelloService, ProdOrdinatiRepository prodOrdinatiRepository) {
        this.ordineRepository = ordineRepository;
        this.prodottoRepository = prodottoRepository;
        this.dettaglioCarrelloService = dettaglioCarrelloService;
        this.prodOrdinatiRepository = prodOrdinatiRepository;
    }

    public Dati_di_pagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<Ordine> findOrdini(Date data){

        return ordineRepository.findOrdinesByData(data);
    }

    @Transactional
    public void createOrdine(Utente utente, List<Dettaglio_carrello>dettagliocarrelloList, Indirizzo_di_spedizione indirizzodispedizione, Dati_di_pagamento datidipagamento) throws ProdottoNotAvaible {

        int totale = 0;
        Ordine ordine = new Ordine();
        ordine.setIdUtente(utente);
        ordine.setIdIndirizzo(indirizzodispedizione);
        ordine.setIdPagamento(datidipagamento);

        HashMap<Integer,Integer> prodotti = new HashMap<>();
        for(Dettaglio_carrello dc: dettagliocarrelloList){
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
        Date d = new Date();
        ordine.setData(d);
        ordineRepository.save(ordine);
       List<Prod_ordinati> prodOrdinatiList = new LinkedList<>();
        for(Dettaglio_carrello dc: dettagliocarrelloList){
            Prod_ordinati prodOrdinati = new Prod_ordinati();

            prodOrdinati.setOrdine(ordine);
            prodOrdinati.setProdotto(dc.getIdProdotto());
            prodOrdinati.setPrezzo(dc.getPrezzo());
            prodOrdinati.setQuantita(dc.getQuantità());
            prodOrdinatiRepository.save(prodOrdinati);
            prodOrdinatiList.add(prodOrdinati);


        }

        ordine.setProdOrdinati(prodOrdinatiList);
        dettaglioCarrelloService.eliminaDettagli(dettagliocarrelloList);

    }

    public List<Ordine> getOrdini(Utente utente){
        return ordineRepository.findOrdinesByIdUtente(utente);
    }

}
