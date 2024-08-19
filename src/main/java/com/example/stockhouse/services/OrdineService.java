package com.example.stockhouse.services;

import com.example.stockhouse.entities.DatiDiPagamento;
import com.example.stockhouse.entities.Ordine;
import com.example.stockhouse.repositories.OrdineRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdineService {
    private OrdineRepository ordineRepository;

    public DatiDiPagamento findPagamento(int idOrdine){

        return ordineRepository.findIdPagamentoByIdOrdine(idOrdine);
    }

    public List<Ordine> findOrdini(Date data){
        return ordineRepository.findOrdinesByData(data);
    }

}
