package com.example.stockhouse.services;


import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.DettaglioCarrello;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.DettaglioCarrelloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DettaglioCarrelloService {

    private DettaglioCarrelloRepository dettaglioCarrelloRepository;

    public List<DettaglioCarrello> findDettagli(Carrello carrello){
        return dettaglioCarrelloRepository.findByIdCarrello(carrello);
    }
    public DettaglioCarrello findDettaglio(Carrello carrello, Prodotto prodotto){
        return dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
    }
    public Prodotto findProdotto(int id_dettaglio){
        return dettaglioCarrelloRepository.findProdottoByIdDettaglio(id_dettaglio);
    }

    public void createDettaglioCarrello(Carrello carrello, Prodotto prodotto, int quantita){
        if(dettaglioCarrelloRepository.existByIdCarrelloAndIdProdotto( carrello, prodotto)){
            DettaglioCarrello dt = dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
            dt.setQunatità(dt.getQunatità()+quantita);

        }
        else{
            DettaglioCarrello dettaglioCarrello = new DettaglioCarrello();
            dettaglioCarrello.setIdCarrello(carrello);
            dettaglioCarrello.setIdProdotto(prodotto);
            dettaglioCarrello.setQunatità(quantita);
        }
    }
}
