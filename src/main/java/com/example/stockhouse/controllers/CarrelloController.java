package com.example.stockhouse.controllers;

import com.example.stockhouse.dtos.ProdottosDTO;
import com.example.stockhouse.entities.*;
import com.example.stockhouse.mappers.ProdottosMapper;
import com.example.stockhouse.services.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController("carrello")

public class CarrelloController {

    private final CarrelloService carrelloService;
    private final DettaglioCarrelloService dettaglioCarrelloService;
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final IndirizzoDiSpedizioneService indirizzoDiSpedizioneService;
    private final DatiDiPagamentoService datiDiPagamentoService;



    private final  OrdineService ordineService;

    public CarrelloController(CarrelloService carrelloService,
                              DettaglioCarrelloService dettaglioCarrelloService,
                              UtenteService utenteService,
                              ProdottoService prodottoService,
                              IndirizzoDiSpedizioneService indirizzoDiSpedizioneService,
                              DatiDiPagamentoService datiDiPagamentoService,

                              OrdineService ordineService) {
        this.carrelloService = carrelloService;
        this.dettaglioCarrelloService = dettaglioCarrelloService;
        this.utenteService = utenteService;
        this.prodottoService = prodottoService;
        this.indirizzoDiSpedizioneService = indirizzoDiSpedizioneService;
        this.datiDiPagamentoService = datiDiPagamentoService;

        this.ordineService = ordineService;
    }

    @PostMapping("add")
    public ResponseEntity<?> addCarrello(
            @RequestParam("utente") @NotNull int utente,
            @RequestParam("prodotto")@NotNull int prodotto,
            @RequestParam("quantita")@NotNull int quantita
    ){
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            System.out.println("utente vuoto");
            return ResponseEntity.badRequest().build();
        }
        Optional<Prodotto> p = prodottoService.getProd(prodotto);
        if(p.isEmpty()){
            System.out.println("non c'è il prodotto");
            return ResponseEntity.badRequest().build();
        }
        if(p.get().getQuantita()<quantita){
            System.out.println("non c'è la quantita");
            return ResponseEntity.badRequest().build(); //TODO ritornare qualcosa di specifico
        }
        try {
            dettaglioCarrelloService.createDettaglioCarrello(u.get().getCarrello(), p.get(), quantita, p.get().getPrezzo());
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("elenca")
    public ResponseEntity<?>getDettagli(
            @RequestParam("utente") @NotNull int utente
    ){
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<Dettaglio_carrello> d = u.get().getCarrello().getDettagliocarrelloList();
        return ResponseEntity.ok(d);
    }

    @PostMapping("ordina")
    public ResponseEntity<?> ordina(
            @RequestParam("utente") @NotNull int utente,
            @RequestParam("indirizzo")@NotNull int id_indirizzo,
            @RequestParam("pagamento")@NotNull int id_pagamento,
            @RequestBody @NotNull ProdottosDTO prodottosDTO
            ){
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        if (!indirizzoDiSpedizioneService.utenteHaIndirizzo(u.get(),id_indirizzo)){
            return ResponseEntity.badRequest().build();
        }
        if(!datiDiPagamentoService.utenteHaPagamento(u.get(),id_pagamento)){
            return ResponseEntity.badRequest().build();
        }
        List<Prodotto> prodottoList = ProdottosMapper.aProdotto(prodottosDTO);
        for(Prodotto p: prodottoList ){
            if(!prodottoService.esiste(p.getId())){
                return ResponseEntity.badRequest().build();
            }
        }
        List<Dettaglio_carrello> dettaglio_carrelloList = new LinkedList<>();
        System.out.println("arriva0");
        for(Prodotto p: prodottoList ){
            System.out.println("arriva2");
            dettaglio_carrelloList.add( dettaglioCarrelloService.createDettaglioCarrello1(u.get().getCarrello(),p.getId(),p.getQuantita()));
        }
        try {
            System.out.println("arriva1");
            ordineService.createOrdine(u.get(),dettaglio_carrelloList, indirizzoDiSpedizioneService.find(id_indirizzo,u.get()),datiDiPagamentoService.find(u.get(),id_pagamento) );

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


}
