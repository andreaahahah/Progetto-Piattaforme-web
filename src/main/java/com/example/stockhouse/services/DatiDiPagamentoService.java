package com.example.stockhouse.services;

import com.example.stockhouse.entities.Dati_di_pagamento;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.DatoDiPagamentoAlreadyExist;
import com.example.stockhouse.repositories.DatiDiPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DatiDiPagamentoService {

    private final DatiDiPagamentoRepository datiDiPagamentoRepository;

    public DatiDiPagamentoService(DatiDiPagamentoRepository datiDiPagamentoRepository) {
        this.datiDiPagamentoRepository = datiDiPagamentoRepository;
    }

    public List<Dati_di_pagamento> findDatiDiPagamento(Utente idUtente){
        return datiDiPagamentoRepository.findByIdUtente(idUtente);
    }

    public void createDatoDiPagamento(Utente utente, String numeroCarta, Date dataScadenza, String tipoCarta, String nomeCarta) throws DatoDiPagamentoAlreadyExist {
        if(datiDiPagamentoRepository.findByIdUtenteAndNumeroCarta(utente, numeroCarta) == null){
            Dati_di_pagamento datidipagamento = new Dati_di_pagamento();
            datidipagamento.setIdUtente(utente);
            datidipagamento.setNumeroCarta(numeroCarta);
            datidipagamento.setDataScadenza((java.sql.Date) dataScadenza);
            datidipagamento.setTipoCarta(tipoCarta);
            datidipagamento.setNomeCarta(nomeCarta);
            datiDiPagamentoRepository.save(datidipagamento);
        }
        else{
            throw new DatoDiPagamentoAlreadyExist();
        }
    }
}
