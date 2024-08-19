package com.example.stockhouse.services;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.ProdCateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdCateService {
    private ProdCateRepository prodCateRepository;

    public List<Prodotto> findProdotti(CategoriaProdotto categoria){
        return prodCateRepository.findProdCatesByIdCategoria(categoria);
    }
}
