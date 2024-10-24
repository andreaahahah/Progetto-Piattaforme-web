package com.example.stockhouse.services;

import com.example.stockhouse.entities.marca;
import com.example.stockhouse.entities.prodotto;
import com.example.stockhouse.exceptions.ProdottoNotExist;
import com.example.stockhouse.repositories.ProdottoRepository;
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

    private final ProdottoRepository prodottoRepository;

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    @Transactional(readOnly = true)
    public List<prodotto> showAllProducts(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<prodotto> pagedResult = prodottoRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<prodotto> showProductsByName(String name) {
        return prodottoRepository.findProdottosByNome(name);
    }

    @Transactional(readOnly = true)
    public List<prodotto> showProductsByMarca(marca marca) {
        return prodottoRepository.findProdottosByMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<prodotto> showProductsByNameAndDescription(String name, String description) {
        return prodottoRepository.advancedSearch(name, description);
    }

    @Transactional(readOnly = true)
    public boolean isProductAvailable(int productId) {
        return prodottoRepository.existsByIdAndQuantitaGreaterThan(productId, 0);
    }

    @Transactional(readOnly = true)
    public List<prodotto> showProductsAvailable() {
        return prodottoRepository.findProdottosWithPositiveQuantita();
    }

    public void createProdotto(String nome, Integer prezzo, String descrizione, String immagini, Integer quantita, marca brand) {
        if (prodottoRepository.findByNomeAndDescrizioneAndMarca(nome, descrizione, brand) == null) {
            prodotto prod = new prodotto();
            prod.setNome(nome);
            prod.setPrezzo(prezzo);
            prod.setDescrizione(descrizione);
            prod.setImmagini(immagini);
            prod.setQuantita(quantita);
            prod.setMarca(brand);
            prodottoRepository.save(prod);
        } else {
            prodotto p = prodottoRepository.findByNomeAndDescrizioneAndMarca(nome, descrizione, brand);
            p.setQuantita(p.getQuantita() + 1);
            prodottoRepository.save(p);

        }
    }

    public void addQuantita(prodotto prod, int quantita) throws ProdottoNotExist {
        if (prodottoRepository.findById(prod.getId()).isPresent()) {
            prodotto p = prodottoRepository.findProdottoById(prod.getId());
            p.setQuantita(prod.getQuantita() + quantita);
        } else {
            throw new ProdottoNotExist();
        }

    }

    @Transactional(readOnly = true)
    public List<prodotto> showVetrina() {
        return prodottoRepository.findProdottosByVetrinaIsTrue();
    }

    public void setVetrina(int prod) throws ProdottoNotExist {

        if (prodottoRepository.existsById(prod)) {
           prodotto p = prodottoRepository.findProdottoById(prod);
            p.setVetrina(true);
            prodottoRepository.save(p);
        } else {
            throw new ProdottoNotExist();
        }
    }
}

