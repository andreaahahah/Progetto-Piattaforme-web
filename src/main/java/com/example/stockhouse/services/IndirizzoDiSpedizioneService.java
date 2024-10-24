package com.example.stockhouse.services;

import com.example.stockhouse.entities.indirizzo_di_spedizione;
import com.example.stockhouse.entities.utente;
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

    public List<indirizzo_di_spedizione> findIndirizzi(utente utente){
        return indirizzoDiSpedizioneRepository.findByIdUtente(utente);
    }
    public void createIndirizzoDiSpedizione(utente utente, String via, String città, String cap, String nazione) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            indirizzo_di_spedizione indirizzodispedizione = new indirizzo_di_spedizione();
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

    public void createIndirizzoDiSpedizione(utente utente, String via, String città, String cap, String nazione, String note) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            indirizzo_di_spedizione indirizzo_di_spedizione = new indirizzo_di_spedizione();
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
