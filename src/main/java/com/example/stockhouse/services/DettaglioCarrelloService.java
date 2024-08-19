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
        return dettaglioCarrelloRepository.findByCarrello(carrello);
    }
    public DettaglioCarrello findDettaglio(Carrello carrello, Prodotto prodotto){
        return dettaglioCarrelloRepository.findByCarrelloAndProdotto(carrello, prodotto);
    }
    public Prodotto findProdotto(int id_dettaglio){
        return dettaglioCarrelloRepository.findProdottoByIdDettaglio(id_dettaglio);
    }
}
