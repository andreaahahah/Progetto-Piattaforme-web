package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.services.DatiDiPagamentoService;
import com.example.stockhouse.services.IndirizzoDiSpedizioneService;
import com.example.stockhouse.services.UtenteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController("utente")
public class UtenteController {

    private  final UtenteService utenteService;
    private final DatiDiPagamentoService datiDiPagamentoService;
    private final IndirizzoDiSpedizioneService indirizzoDiSpedizioneService;

    public UtenteController(UtenteService utenteService, DatiDiPagamentoService datiDiPagamentoService, IndirizzoDiSpedizioneService indirizzoDiSpedizioneService) {
        this.utenteService = utenteService;
        this.datiDiPagamentoService = datiDiPagamentoService;
        this.indirizzoDiSpedizioneService = indirizzoDiSpedizioneService;
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
            @RequestParam("utente") @NotNull int utente
    ){
        Optional<Utente> u = utenteService.findUtente(utente);

        if(u.isEmpty()){

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(u.get());
    }
    @PostMapping("addPagamento")
    public ResponseEntity<?> addPagamento(
            @RequestParam("utente")@NotNull Utente utente,
            @RequestParam("n") @NotNull String numero,
            @RequestParam("data")@NotNull Date data,
            @RequestParam("tipo")@NotNull String tipo,
            @RequestParam("nome") @NotNull String nome
    ){
      //TODO sostituisci l'utente con il suo token
        try{
            datiDiPagamentoService.createDatoDiPagamento(utente,numero,data,tipo,nome);

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("getPagamento")
    public ResponseEntity<?> getPagamento(
            @RequestParam("utente") @NotNull int utente
    ){
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( datiDiPagamentoService.findDatiDiPagamento(u.get()));
    }

}
