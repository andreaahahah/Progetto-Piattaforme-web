package com.example.stockhouse.services;

import com.example.stockhouse.entities.*;
import com.example.stockhouse.exceptions.ProdottoNotAvaible;
import com.example.stockhouse.repositories.OrdineRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrdineService {
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;

    public OrdineService(OrdineRepository ordineRepository, ProdottoRepository prodottoRepository) {
        this.ordineRepository = ordineRepository;
        this.prodottoRepository = prodottoRepository;
    }

    public DatiDiPagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<Ordine> findOrdini(Date data){

        return ordineRepository.findOrdinesByData(data);
    }

    public void createOrdine(Utente utente, Carrello carrello, IndirizzoDiSpedizione indirizzoDiSpedizione, DatiDiPagamento datiDiPagamento) throws ProdottoNotAvaible {
        //TODO VERIFICARE SE IL CARRELLO HA PRODOTTI CON QUANTITA' POSITIVA

        Ordine ordine = new Ordine();
        ordine.setIdUtente(utente);
        //ordine.setIdCarrello(carrello);
        ordine.setIdIndirizzo(indirizzoDiSpedizione);
        ordine.setIdPagamento(datiDiPagamento);
        HashMap<Integer,Integer> prodotti = new HashMap<>();
        for(DettaglioCarrello dc: carrello.getDettaglioCarrelloList()){
            prodotti.put(dc.getIdProdotto().getId(),dc.getQuantità());
        }
        List<Prodotto> prodLock = prodottoRepository.findProdottosByIdIn(prodotti.keySet());
        for(Prodotto p: prodLock){
            if(prodottoRepository.existsByIdAndQuantitaGreaterThan(p.getId(),p.getQuantita())){
                p.setQuantita(p.getQuantita()- prodotti.get(p.getId()));}
            else{
                throw new ProdottoNotAvaible();
            }
        }
        prodottoRepository.saveAll(prodLock);
        ordine.setListaProdotti(prodLock);
        ordineRepository.save(ordine);

    }

}
