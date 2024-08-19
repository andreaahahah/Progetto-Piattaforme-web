package com.example.stockhouse.services;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Utente;
import com.example.stockhouse.repositories.DatiDiPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatiDiPagamentoService {

    private DatiDiPagamentoRepository datiDiPagamentoRepository;

    public List<DatiDiPagamento> findDatiDiPagamento(Utente idUtente){
        return datiDiPagamentoRepository.findByIdUtente(idUtente);
    }
}
