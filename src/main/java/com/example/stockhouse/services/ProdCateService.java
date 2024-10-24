package com.example.stockhouse.services;

import com.example.stockhouse.entities.categoria_prodotto;
import com.example.stockhouse.entities.prodotto;
import com.example.stockhouse.repositories.ProdCateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdCateService {
    private final ProdCateRepository prodCateRepository;

    public ProdCateService(ProdCateRepository prodCateRepository) {
        this.prodCateRepository = prodCateRepository;
    }

    public List<prodotto> findProdotti(categoria_prodotto categoria){
        return prodCateRepository.findProdottosByIdCategoria(categoria);
    }

    public List<categoria_prodotto> findCategorie(prodotto prodotto){
        return prodCateRepository.findCategoriaByIdProdotto(prodotto);
    }
}
