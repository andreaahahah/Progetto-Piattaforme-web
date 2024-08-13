package com.example.stockhouse.services;

import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional(readOnly = true)
    public List<Prodotto> showAllProducts(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = prodottoRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>(); // non fare mai i return del null, ritorna una lista vuota che è meglio
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByName(String name){
        return prodottoRepository.findProdottosByNome(name);
    }
    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByMarca(String marca){
        return prodottoRepository.findProdottosByMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByNameAndDescription(String name, String description) {
        return prodottoRepository.advancedSearch(name, description);
    }
    @Transactional(readOnly = true)
    public boolean isProductAvailable(Long productId) {
        return prodottoRepository.existsByIdAndQuantityGreaterThan(productId, 0);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsAvailable() {
        return prodottoRepository.findProdottosWithPositiveQuantity();
    }
    }
