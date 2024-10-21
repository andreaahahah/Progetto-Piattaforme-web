package com.example.stockhouse.services;


import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.DettaglioCarrello;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.exceptions.ProdottoNotExist;
import com.example.stockhouse.repositories.DettaglioCarrelloRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<DettaglioCarrello> findDettagli(Carrello carrello){
        return dettaglioCarrelloRepository.findByIdCarrello(carrello);
    }
    public DettaglioCarrello findDettaglio(Carrello carrello, Prodotto prodotto){
        return dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
    }
    public Prodotto findProdotto(int id_dettaglio){
        return dettaglioCarrelloRepository.findProdottoByIdDettaglio(id_dettaglio);
    }

    public void createDettaglioCarrello(Carrello carrello, Prodotto prodotto, int quantita) throws ProdottoNotExist {
        DettaglioCarrello dettaglioCarrello = null;
        if(dettaglioCarrelloRepository.existByIdCarrelloAndIdProdotto( carrello, prodotto)){
            dettaglioCarrello = dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
            dettaglioCarrello.setQuantità(dettaglioCarrello.getQuantità()+quantita);
        }
        else{
            if(prodottoRepository.existsById(prodotto.getId())){
            dettaglioCarrello = new DettaglioCarrello();
            dettaglioCarrello.setIdCarrello(carrello);
            dettaglioCarrello.setIdProdotto(prodotto);
            dettaglioCarrello.setQuantità(quantita);}
            else{
                throw new ProdottoNotExist();
            }
        }
        dettaglioCarrelloRepository.save(dettaglioCarrello);
    }
}
