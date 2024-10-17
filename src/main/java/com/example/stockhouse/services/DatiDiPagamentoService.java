package com.example.stockhouse.services;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.exceptions.DatoDiPagamentoAlreadyExist;
import com.example.stockhouse.repositories.DatiDiPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DatiDiPagamentoService {

    private DatiDiPagamentoRepository datiDiPagamentoRepository;

    public List<DatiDiPagamento> findDatiDiPagamento(Utente idUtente){
        return datiDiPagamentoRepository.findByIdUtente(idUtente);
    }

    public void createDatoDiPagamento(Utente utente, String numeroCarta, Date dataScadenza, String tipoCarta,String nomeCarta) throws DatoDiPagamentoAlreadyExist {
        if(datiDiPagamentoRepository.findByIdUtenteAndnAndNumeroCarta(utente, numeroCarta) == null){
            DatiDiPagamento datiDiPagamento = new DatiDiPagamento();
            datiDiPagamento.setIdUtente(utente);
            datiDiPagamento.setNumeroCarta(numeroCarta);
            datiDiPagamento.setDataScadenza((java.sql.Date) dataScadenza);
            datiDiPagamento.setTipoCarta(tipoCarta);
            datiDiPagamento.setNomeCarta(nomeCarta);
        }
        else{
            throw new DatoDiPagamentoAlreadyExist();
        }
    }
}
