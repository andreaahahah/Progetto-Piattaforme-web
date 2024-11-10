package com.example.stockhouse.services;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.entities.Prodotto;
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
import java.util.Optional;

@Service
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showAllProducts(int pageNumber, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = prodottoRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
    @Transactional(readOnly = true)
    public Optional<Prodotto> getProd(int id){
        return prodottoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public boolean esiste(int id){
        return prodottoRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByName(String name) {
        return prodottoRepository.findProdottosByNome(name);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByMarca(Marca marca) {
        return prodottoRepository.findProdottosByMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsByNameS(String name) {
        return prodottoRepository.advancedSearch(name);
    }

    @Transactional(readOnly = true)
    public boolean isProductAvailable(int productId) {
        return prodottoRepository.existsByIdAndQuantitaGreaterThan(productId, 0);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProductsAvailable() {
        return prodottoRepository.findProdottosWithPositiveQuantita();
    }

    public int createProdotto(String nome, Integer prezzo, String descrizione, String immagini, Integer quantita, Marca brand) {
        if (prodottoRepository.findByNomeAndDescrizioneAndMarca(nome, descrizione, brand) == null) {
            Prodotto prod = new Prodotto();
            prod.setNome(nome);
            prod.setPrezzo(prezzo);
            prod.setDescrizione(descrizione);
            prod.setImmagini(immagini);
            prod.setQuantita(quantita);
            prod.setMarca(brand);
            prodottoRepository.save(prod);
            return prod.getId();
        } else {
            Prodotto p = prodottoRepository.findByNomeAndDescrizioneAndMarca(nome, descrizione, brand);
            p.setQuantita(p.getQuantita() + quantita);
            prodottoRepository.save(p);
            return  p.getId();

        }

    }

    public void addQuantita(Prodotto prod, int quantita) throws ProdottoNotExist {
        if (prodottoRepository.findById(prod.getId()).isPresent()) {
            Prodotto p = prodottoRepository.findProdottoById(prod.getId());
            p.setQuantita(prod.getQuantita() + quantita);
        } else {
            throw new ProdottoNotExist();
        }

    }

    @Transactional(readOnly = true)
    public List<Prodotto> showVetrina() {
        return prodottoRepository.findProdottosByVetrinaIsTrue();
    }

    public void setVetrina(int prod) throws ProdottoNotExist {

        if (prodottoRepository.existsById(prod)) {
           Prodotto p = prodottoRepository.findProdottoById(prod);
            p.setVetrina(true);
            prodottoRepository.save(p);
        } else {
            throw new ProdottoNotExist();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showProdByMarca( Marca marca) {
        return prodottoRepository.findProdottosByMarca(marca);
    }
}

