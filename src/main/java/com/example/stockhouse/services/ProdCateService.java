package com.example.stockhouse.services;

import com.example.stockhouse.entities.Categoria_prodotto;
import com.example.stockhouse.entities.Prod_cate;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.ProdCateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdCateService {
    private final ProdCateRepository prodCateRepository;
    private final ProdottoService prodottoService;
    private final CategoriaProdottoService categoriaProdottoService;
    public ProdCateService(ProdCateRepository prodCateRepository, ProdottoService prodottoService, CategoriaProdottoService categoriaProdottoService) {
        this.prodCateRepository = prodCateRepository;
        this.prodottoService = prodottoService;
        this.categoriaProdottoService = categoriaProdottoService;
    }

    public List<Prodotto> findProdotti(Categoria_prodotto categoria){
        return prodCateRepository.findProdottosByIdCategoria(categoria);
    }

    public List<Categoria_prodotto> findCategorie(Prodotto prodotto){
        return prodCateRepository.findCategoriaByIdProdotto(prodotto);
    }

    public boolean addCategoria(int prodotto, int categoria){

        Optional<Prodotto> p = prodottoService.getProd(prodotto);
        Optional<Categoria_prodotto> cp = categoriaProdottoService.getCategoria(categoria);
        if(p.isEmpty() || cp.isEmpty()){
           return false;
        }
        Prod_cate pc = new Prod_cate();
        pc.setIdCategoria(cp.get());
        pc.setIdProdotto(p.get());
        prodCateRepository.save(pc);
        return true;
    }
}
