package com.example.stockhouse.mappers;

import com.example.stockhouse.dtos.ProdottoDTO;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.MarcaRepository;

public class ProdottoMapper {

    private final MarcaRepository marcaRepository;

    public ProdottoMapper(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Prodotto aProdotto(ProdottoDTO prodottoDTO){
        Prodotto p = new Prodotto();
        p.setPrezzo(prodottoDTO.prezzo());
        p.setQuantita(prodottoDTO.quantita());
        p.setNome(prodottoDTO.nome());
        p.setDescrizione(prodottoDTO.descrizione());
        p.setVetrina(prodottoDTO.vetrina());
        p.setMarca(marcaRepository.findMarcaByNome(prodottoDTO.nomeMarca()));
        p.setImmagini(prodottoDTO.immagini());
        return  p;
    }

    public  ProdottoDTO aDto(Prodotto prodotto){
        ProdottoDTO pdt = new ProdottoDTO(
                prodotto.getId(),
                prodotto.getPrezzo(),
                prodotto.getQuantita(),
                prodotto.getNome(),
                prodotto.getDescrizione(),
                prodotto.isVetrina(),
                prodotto.getMarca().getNome(),
                prodotto.getImmagini()
        );
        return pdt;
    }
}