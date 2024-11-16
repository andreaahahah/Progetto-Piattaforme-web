package com.example.stockhouse.controllers;

import com.example.stockhouse.dtos.ProdottosDTO;
import com.example.stockhouse.entities.*;
import com.example.stockhouse.mappers.ProdottosMapper;
import com.example.stockhouse.services.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("carrello")
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
            @RequestParam("prodotto")@NotNull int prodotto,
            @RequestParam("quantita")@NotNull int quantita
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");
        Utente u = utenteService.findUtente(email);

        Optional<Prodotto> p = prodottoService.getProd(prodotto);
        if(p.isEmpty()){

            return ResponseEntity.badRequest().build();
        }
        if(p.get().getQuantita()<quantita){

            return ResponseEntity.badRequest().build(); //TODO ritornare qualcosa di specifico
        }
        try {
            dettaglioCarrelloService.createDettaglioCarrello(u.getCarrello(), p.get(), quantita, p.get().getPrezzo());
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("elenca")
    public ResponseEntity<?>getDettagli(
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");

        Utente u = utenteService.findUtente(email);

        List<Dettaglio_carrello> d = u.getCarrello().getDettagliocarrelloList();
        return ResponseEntity.ok(d);
    }

    @PostMapping("ordina")
    public ResponseEntity<?> ordina(
            @RequestParam("indirizzo")@NotNull int id_indirizzo,
            @RequestParam("pagamento")@NotNull int id_pagamento,
            @RequestBody @NotNull ProdottosDTO prodottosDTO
            ){

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");

        Utente u = utenteService.findUtente(email);

        if (!indirizzoDiSpedizioneService.utenteHaIndirizzo(u,id_indirizzo)){
            return ResponseEntity.badRequest().build();
        }
        if(!datiDiPagamentoService.utenteHaPagamento(u,id_pagamento)){
            return ResponseEntity.badRequest().build();
        }
        System.out.println("i prodotti sono" + prodottosDTO);
        List<Prodotto> prodottoList = ProdottosMapper.aProdotto(prodottosDTO);
        for(Prodotto p: prodottoList ){
            System.out.println("un prodotto non esiste "+!prodottoService.esiste(p.getId()));
            if(!prodottoService.esiste(p.getId())){
                return ResponseEntity.badRequest().build();
            }
        }
        List<Dettaglio_carrello> dettaglio_carrelloList = new LinkedList<>();

        for(Prodotto p: prodottoList ){
            dettaglio_carrelloList.add( dettaglioCarrelloService.createDettaglioCarrello1(u.getCarrello(),p.getId(),p.getQuantita()));
        }

        try {
            System.out.println("entro nel try");
            ordineService.createOrdine(u,dettaglio_carrelloList, indirizzoDiSpedizioneService.find(id_indirizzo,u),datiDiPagamentoService.find(u,id_pagamento) );


        }catch (Exception e){
            System.out.println();
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("rimuovi")
    public ResponseEntity<?> elimina(
            @RequestParam("prodotto")@NotNull int prodotto
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");
        Utente u = utenteService.findUtente(email);
        Optional<Prodotto> p = prodottoService.getProd(prodotto);
        if(p.isEmpty()){
            return ResponseEntity.badRequest().build();

        }
        Carrello carrello = u.getCarrello();
        dettaglioCarrelloService.trovaedElimina(p.get(),carrello);
        return  ResponseEntity.ok().build();

    }
}
