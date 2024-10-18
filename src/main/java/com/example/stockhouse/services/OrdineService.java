package com.example.stockhouse.services;

import com.example.stockhouse.entities.*;
import com.example.stockhouse.repositories.OrdineRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdineService {
    private OrdineRepository ordineRepository;

    public DatiDiPagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<Ordine> findOrdini(Date data){

        return ordineRepository.findOrdinesByData(data);
    }

    public void createOrdine(Utente utente, Carrello carrello, IndirizzoDiSpedizione indirizzoDiSpedizione, DatiDiPagamento datiDiPagamento) {
        //TODO VERIFICARE SE IL CARRELLO HA PRODOTTI CON QUANTITA' POSITIVA

        Ordine ordine = new Ordine();
        ordine.setIdUtente(utente);
        ordine.setIdCarrello(carrello);
        ordine.setIdIndirizzo(indirizzoDiSpedizione);
        ordine.setIdPagamento(datiDiPagamento);

    }

}
