package com.example.stockhouse.services;

import com.example.stockhouse.entities.categoria_prodotto;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.ProdCateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdCateService {
    private final ProdCateRepository prodCateRepository;

    public ProdCateService(ProdCateRepository prodCateRepository) {
        this.prodCateRepository = prodCateRepository;
    }

    public List<Prodotto> findProdotti(categoria_prodotto categoria){
        return prodCateRepository.findProdottosByIdCategoria(categoria);
    }

    public List<categoria_prodotto> findCategorie(Prodotto prodotto){
        return prodCateRepository.findCategoriaByIdProdotto(prodotto);
    }
}
