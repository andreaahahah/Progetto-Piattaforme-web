package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Dati_di_pagamento;
import com.example.stockhouse.entities.Indirizzo_di_spedizione;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.services.DatiDiPagamentoService;
import com.example.stockhouse.services.IndirizzoDiSpedizioneService;
import com.example.stockhouse.services.OrdineService;
import com.example.stockhouse.services.UtenteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("utente")
public class UtenteController {

    private  final UtenteService utenteService;
    private final DatiDiPagamentoService datiDiPagamentoService;
    private final IndirizzoDiSpedizioneService indirizzoDiSpedizioneService;
    private final OrdineService ordineService;

    public UtenteController(UtenteService utenteService, DatiDiPagamentoService datiDiPagamentoService, IndirizzoDiSpedizioneService indirizzoDiSpedizioneService, OrdineService ordineService) {
        this.utenteService = utenteService;
        this.datiDiPagamentoService = datiDiPagamentoService;
        this.indirizzoDiSpedizioneService = indirizzoDiSpedizioneService;
        this.ordineService = ordineService;
    }

    @PostMapping("addUtente")
    public ResponseEntity<?> addUtente(
            @RequestParam("nome") @NotNull String nome,
            @RequestParam("cognome")@NotNull String cognome,
            @RequestParam("email") @NotNull String email
    ){
        try {
            utenteService.createUtente(nome, cognome, email);
        }
        catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("getUtente")
    public ResponseEntity<?> getUtente(
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");

        Utente u = utenteService.findUtente(email);
        return ResponseEntity.ok(u);
    }

    @PostMapping("addPagamento")
    public ResponseEntity<?> addPagamento(
            @RequestParam("n") @NotNull String numero,
            @RequestParam("data")@NotNull String data,//data da inviare in formato YYYY-MM-DD
            @RequestParam("tipo")@NotNull String tipo,
            @RequestParam("nome") @NotNull String nome
    ){


        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");

        Utente u = utenteService.findUtente(email);
        try{
            datiDiPagamentoService.createDatoDiPagamento(u,numero,Date.valueOf(data),tipo,nome);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("getPagamento")
    public ResponseEntity<?> getPagamento(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Recupera l'email dal token JWT
        String email = (String) jwt.getClaims().get("email");

        // Cerca l'utente in base all'email
        Utente u = utenteService.findUtente(email); // Assicurati di avere questo metodo nel servizio utente

        return ResponseEntity.ok( datiDiPagamentoService.findDatiDiPagamento(u));
    }

    @GetMapping("getPagamentoById")
    public ResponseEntity<?> getPagamentoById(
            @RequestParam("id") @NotNull int id
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");
        Utente u = utenteService.findUtente(email);

        List<Dati_di_pagamento> datiDiPagamentoList = datiDiPagamentoService.findDatiDiPagamento(u);
        for (Dati_di_pagamento d:datiDiPagamentoList) {
            if(d.getIdPagamento() == id){
                return ResponseEntity.ok(d);
            }
        }
        return ResponseEntity.badRequest().build();

    }
    @GetMapping("getIndirizzo")
    public ResponseEntity<?> getIndirizzo(

    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Recupera l'email dal token JWT
        String email = (String) jwt.getClaims().get("email");

        // Cerca l'utente in base all'email
        Utente u = utenteService.findUtente(email);
        return ResponseEntity.ok(indirizzoDiSpedizioneService.findIndirizzi(u));
    }

    @GetMapping("getIndirizzoById")
    public ResponseEntity<?> getIndirizzoById(
            @RequestParam("id") @NotNull int id
    ){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");
        Utente u = utenteService.findUtente(email);

        List< Indirizzo_di_spedizione> indirizzoDiSpedizioneList = indirizzoDiSpedizioneService.findIndirizzi(u);
        for (Indirizzo_di_spedizione i:indirizzoDiSpedizioneList) {
            if(i.getId() == id){
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("addIndirizzo")
    public ResponseEntity<?> addIndirizzo(
            @RequestParam("via") @NotNull String via,
            @RequestParam("citta")@NotNull String citta,
            @RequestParam("cap")@NotNull String cap,
            @RequestParam("nazione") @NotNull String nazione,
            @RequestParam(name = "note", required = false)  String note
    )
    {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Recupera l'email dal token JWT
        String email = (String) jwt.getClaims().get("email");

        // Cerca l'utente in base all'email
        Utente u = utenteService.findUtente(email);
        try{
            if(note == null) {
                indirizzoDiSpedizioneService.createIndirizzoDiSpedizione(u,via,citta,cap,nazione);
            }else {
                indirizzoDiSpedizioneService.createIndirizzoDiSpedizione(u,via,citta,cap,nazione,note);
            }

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("ordini")
    public ResponseEntity<?> getOrdini(

    ) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email");
        Utente u = utenteService.findUtente(email);

        return ResponseEntity.ok(ordineService.getOrdini(u));
    }
}
