package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.services.DatiDiPagamentoService;
import com.example.stockhouse.services.IndirizzoDiSpedizioneService;
import com.example.stockhouse.services.OrdineService;
import com.example.stockhouse.services.UtenteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController("utente")
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
            @RequestParam("utente") @NotNull int utente
    ){
        Optional<Utente> u = utenteService.findUtente(utente);

        if(u.isEmpty()){

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(u.get());
    }
    @GetMapping("solotoken")
    public ResponseEntity<?> getEmail() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) jwt.getClaims().get("email"); // Estrai solo l'email

        return ResponseEntity.ok(email); // Restituisci l'email come risposta
    }

    @PostMapping("addPagamento")
    public ResponseEntity<?> addPagamento(
            @RequestParam("utente")@NotNull int utente,
            @RequestParam("n") @NotNull String numero,
            @RequestParam("data")@NotNull String data,//data da inviare in formato YYYY-MM-DD
            @RequestParam("tipo")@NotNull String tipo,
            @RequestParam("nome") @NotNull String nome
    ){
      //TODO sostituisci l'utente con il suo token

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        String email = jwt.getClaim("preferred_username");

        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){

            return ResponseEntity.badRequest().build();
        }
        try{
            datiDiPagamentoService.createDatoDiPagamento(u.get(),numero,Date.valueOf(data),tipo,nome);
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

    @GetMapping("getIndirizzo")
    public ResponseEntity<?> getIndirizzo(
            @RequestParam("utente") @NotNull int utente
    ){
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(indirizzoDiSpedizioneService.findIndirizzi(u.get()));
    }

    @PostMapping("addIndirizzo")
    public ResponseEntity<?> addIndirizzo(
            @RequestParam("utente")@NotNull int utente,
            @RequestParam("via") @NotNull String via,
            @RequestParam("citta")@NotNull String citta,
            @RequestParam("cap")@NotNull String cap,
            @RequestParam("nazione") @NotNull String nazione,
            @RequestParam(name = "note", required = false)  String note
    )
    {
        Optional<Utente> u = utenteService.findUtente(utente);
        if(u.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        try{
            if(note == null) {
                indirizzoDiSpedizioneService.createIndirizzoDiSpedizione(u.get(),via,citta,cap,nazione);
            }else {
                indirizzoDiSpedizioneService.createIndirizzoDiSpedizione(u.get(),via,citta,cap,nazione,note);
            }

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("odini")
    public ResponseEntity<?> getOrdini(
            @RequestParam("utente") @NotNull int utente
    ) {
        Optional<Utente> u = utenteService.findUtente(utente);
        if (u.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ordineService.getOrdini(u.get()));
    }
}
