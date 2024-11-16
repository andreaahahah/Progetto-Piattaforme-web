package com.example.stockhouse.services;


import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.Dettaglio_carrello;
import com.example.stockhouse.entities.Ordine;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.exceptions.ProdottoNotExist;
import com.example.stockhouse.repositories.DettaglioCarrelloRepository;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DettaglioCarrelloService {
    private final DettaglioCarrelloRepository dettaglioCarrelloRepository;
    private final ProdottoRepository prodottoRepository;
    public DettaglioCarrelloService(DettaglioCarrelloRepository dettaglioCarrelloRepository, ProdottoRepository prodottoRepository){
        this.dettaglioCarrelloRepository = dettaglioCarrelloRepository;
        this.prodottoRepository = prodottoRepository;
    }


    public List<Dettaglio_carrello> findDettagli(Carrello carrello){
        return dettaglioCarrelloRepository.findByIdCarrello(carrello);
    }
    public Dettaglio_carrello findDettaglio(Carrello carrello, Prodotto prodotto){
        return dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
    }
    public Prodotto findProdotto(int id_dettaglio){
        return dettaglioCarrelloRepository.findProdottoByIdDettaglio(id_dettaglio);
    }

    public void createDettaglioCarrello(Carrello carrello, Prodotto prodotto, int quantita, int prezzo) throws ProdottoNotExist {
        Dettaglio_carrello dettagliocarrello = null;
        if(dettaglioCarrelloRepository.existsByIdCarrelloAndAndIdProdotto( carrello, prodotto)){
            dettagliocarrello = dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
            dettagliocarrello.setQuantità(dettagliocarrello.getQuantità()+quantita);
        }
        else{
            if(prodottoRepository.existsById(prodotto.getId())){
                dettagliocarrello = new Dettaglio_carrello();
                dettagliocarrello.setIdCarrello(carrello);
                dettagliocarrello.setIdProdotto(prodotto);
                dettagliocarrello.setQuantità(quantita);
                dettagliocarrello.setPrezzo(prezzo);
            }
            else{
                throw new ProdottoNotExist();
            }
        }
        dettaglioCarrelloRepository.save(dettagliocarrello);
    }

    public  Dettaglio_carrello createDettaglioCarrello1(Carrello carrello, int prodotto, int quantita){
        //verifica se esiste già quel prodotto in quel carrello
        Dettaglio_carrello dettagliocarrello = null;
        dettagliocarrello = new Dettaglio_carrello();
        dettagliocarrello.setIdCarrello(carrello);
        dettagliocarrello.setIdProdotto(prodottoRepository.findProdottoById(prodotto));
        dettagliocarrello.setQuantità(quantita);
        int prezzo = prodottoRepository.findProdottoById(prodotto).getPrezzo();
        dettagliocarrello.setPrezzo(prezzo);
        dettaglioCarrelloRepository.save(dettagliocarrello);
        return dettagliocarrello;
    }


    public void eliminaDettagli(List<Dettaglio_carrello> dc){
        dettaglioCarrelloRepository.deleteAll(dc);
    }

    public void trovaedElimina(Prodotto prodotto, Carrello carrello){
        if(dettaglioCarrelloRepository.existsByIdCarrelloAndAndIdProdotto(carrello, prodotto)){
        Dettaglio_carrello d = dettaglioCarrelloRepository.findByIdCarrelloAndIdProdotto(carrello, prodotto);
        dettaglioCarrelloRepository.delete(d);
        }

    }
}
