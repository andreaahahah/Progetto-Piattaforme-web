package com.example.stockhouse.services;

import com.example.stockhouse.entities.Indirizzo_di_spedizione;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.IndirizzoDiSpedizioneAlreadyExist;
import com.example.stockhouse.repositories.IndirizzoDiSpedizioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoDiSpedizioneService {

    private final IndirizzoDiSpedizioneRepository indirizzoDiSpedizioneRepository;

    public IndirizzoDiSpedizioneService(IndirizzoDiSpedizioneRepository indirizzoDiSpedizioneRepository) {
        this.indirizzoDiSpedizioneRepository = indirizzoDiSpedizioneRepository;
    }

    public List<Indirizzo_di_spedizione> findIndirizzi(Utente utente){
        return indirizzoDiSpedizioneRepository.findByIdUtente(utente);
    }
    public void createIndirizzoDiSpedizione(Utente utente, String via, String città, String cap, String nazione) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            Indirizzo_di_spedizione indirizzodispedizione = new Indirizzo_di_spedizione();
            indirizzodispedizione.setIdUtente(utente);
            indirizzodispedizione.setVia(via);
            indirizzodispedizione.setCittà(città);
            indirizzodispedizione.setCap(cap);
            indirizzodispedizione.setNazione(nazione);
            indirizzoDiSpedizioneRepository.save(indirizzodispedizione);
        }
        else{
            throw new IndirizzoDiSpedizioneAlreadyExist();
        }
    }

    public void createIndirizzoDiSpedizione(Utente utente, String via, String città, String cap, String nazione, String note) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            Indirizzo_di_spedizione indirizzo_di_spedizione = new Indirizzo_di_spedizione();
            indirizzo_di_spedizione.setIdUtente(utente);
            indirizzo_di_spedizione.setVia(via);
            indirizzo_di_spedizione.setCittà(città);
            indirizzo_di_spedizione.setCap(cap);
            indirizzo_di_spedizione.setNazione(nazione);
            indirizzo_di_spedizione.setNote(note);
            indirizzoDiSpedizioneRepository.save(indirizzo_di_spedizione);
        }
        else{
            throw new IndirizzoDiSpedizioneAlreadyExist();
        }
    }
}
