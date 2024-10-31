package com.example.stockhouse.mappers;

import com.example.stockhouse.dtos.ProdottoDTO;
import com.example.stockhouse.dtos.ProdottosDTO;
import com.example.stockhouse.entities.Prodotto;

import java.util.LinkedList;
import java.util.List;

public class ProdottosMapper {



    public static List<Prodotto> aProdotto(ProdottosDTO prodottosDTO){
        List<Prodotto> ret = new LinkedList<>();
        for(ProdottoDTO pd: prodottosDTO.prodottoDTOList()){
            ret.add(ProdottoMapper.aProdotto(pd));
        }
        return ret;
    }

}
