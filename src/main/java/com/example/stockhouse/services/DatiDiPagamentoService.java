package com.example.stockhouse.services;

import com.example.stockhouse.entities.dati_di_pagamento;
import com.example.stockhouse.entities.utente;
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

    public List<dati_di_pagamento> findDatiDiPagamento(utente idUtente){
        return datiDiPagamentoRepository.findByIdUtente(idUtente);
    }

    public void createDatoDiPagamento(utente utente, String numeroCarta, Date dataScadenza, String tipoCarta, String nomeCarta) throws DatoDiPagamentoAlreadyExist {
        if(datiDiPagamentoRepository.findByIdUtenteAndNumeroCarta(utente, numeroCarta) == null){
            dati_di_pagamento datidipagamento = new dati_di_pagamento();
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
