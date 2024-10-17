package com.example.stockhouse.services;

import com.example.stockhouse.entities.IndirizzoDiSpedizione;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.IndirizzoDiSpedizioneAlreadyExist;
import com.example.stockhouse.repositories.IndirizzoDiSpedizioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoDiSpedizioneService {

    private IndirizzoDiSpedizioneRepository indirizzoDiSpedizioneRepository;

    public List<IndirizzoDiSpedizione> findIndirizzi(Utente utente){
        return indirizzoDiSpedizioneRepository.findByIdUtente(utente);
    }
    public void createIndirizzoDiSpedizione(Utente utente, String via,String città,String cap, String nazione) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            IndirizzoDiSpedizione IndirizzoDiSpedizione = new IndirizzoDiSpedizione();
            IndirizzoDiSpedizione.setIdUtente(utente);
            IndirizzoDiSpedizione.setVia(via);
            IndirizzoDiSpedizione.setCittà(città);
            IndirizzoDiSpedizione.setCap(cap);
            IndirizzoDiSpedizione.setNazione(nazione);
        }
        else{
            throw new IndirizzoDiSpedizioneAlreadyExist();
        }
    }

    public void createIndirizzoDiSpedizione(Utente utente, String via,String città,String cap, String nazione, String note) throws IndirizzoDiSpedizioneAlreadyExist {
        if(indirizzoDiSpedizioneRepository.findByIdUtenteAndVia(utente, via) == null){
            IndirizzoDiSpedizione IndirizzoDiSpedizione = new IndirizzoDiSpedizione();
            IndirizzoDiSpedizione.setIdUtente(utente);
            IndirizzoDiSpedizione.setVia(via);
            IndirizzoDiSpedizione.setCittà(città);
            IndirizzoDiSpedizione.setCap(cap);
            IndirizzoDiSpedizione.setNazione(nazione);
            IndirizzoDiSpedizione.setNote(note);
        }
        else{
            throw new IndirizzoDiSpedizioneAlreadyExist();
        }
    }
}
