package com.example.stockhouse.services;


import com.example.stockhouse.entities.carrello;
import com.example.stockhouse.entities.dettaglio_carrello;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.exceptions.ProdottoNotExist;
import com.example.stockhouse.repositories.DettaglioCarrelloRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DettaglioCarrelloService {
    private final DettaglioCarrelloRepository dettaglioCarrelloRepository;
    private final ProdottoRepository prodottoRepository;
    public DettaglioCarrelloService(DettaglioCarrelloRepository dettaglioCarrelloRepository, ProdottoRepository prodottoRepository){
        this.dettaglioCarrelloRepository = dettaglioCarrelloRepository;
        this.prodottoRepository = prodottoRepository;
    }


    public List<dettaglio_carrello> findDettagli(carrello carrello){
        return dettaglioCarrelloRepository.findByIdCarrello(carrello);
    }
    public dettaglio_carrello findDettaglio(carrello carrello, Prodotto prodotto){
        return dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
    }
    public Prodotto findProdotto(int id_dettaglio){
        return dettaglioCarrelloRepository.findProdottoByIdDettaglio(id_dettaglio);
    }

    public void createDettaglioCarrello(carrello carrello, Prodotto prodotto, int quantita) throws ProdottoNotExist {
        dettaglio_carrello dettagliocarrello = null;
        if(dettaglioCarrelloRepository.existsByIdCarrelloAndAndIdProdotto( carrello, prodotto)){
            dettagliocarrello = dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
            dettagliocarrello.setQuantità(dettagliocarrello.getQuantità()+quantita);
        }
        else{
            if(prodottoRepository.existsById(prodotto.getId())){
            dettagliocarrello = new dettaglio_carrello();
            dettagliocarrello.setIdCarrello(carrello);
            dettagliocarrello.setIdProdotto(prodotto);
            dettagliocarrello.setQuantità(quantita);}
            else{
                throw new ProdottoNotExist();
            }
        }
        dettaglioCarrelloRepository.save(dettagliocarrello);
    }
}
