package com.example.stockhouse.mappers;

import com.example.stockhouse.dtos.ProdottoDTO;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.MarcaRepository;

public class ProdottoMapper {

    public static Prodotto aProdotto(ProdottoDTO prodottoDTO){
        Prodotto p = new Prodotto();
        p.setId(prodottoDTO.id());
        p.setQuantita(prodottoDTO.quantita());
        return  p;
    }

    public static ProdottoDTO aDto(Prodotto prodotto){
        ProdottoDTO pdt = new ProdottoDTO(
                prodotto.getId(),
                prodotto.getQuantita()
        );
        return pdt;
    }
}
