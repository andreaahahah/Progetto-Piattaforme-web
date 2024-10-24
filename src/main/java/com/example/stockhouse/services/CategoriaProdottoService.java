package com.example.stockhouse.services;

import com.example.stockhouse.entities.categoria_prodotto;
import com.example.stockhouse.exceptions.CategoriaProdottoAlreadyExist;
import com.example.stockhouse.repositories.CategoriaProdottoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProdottoService {

    private final CategoriaProdottoRepository categoriaProdottoRepository;

    public CategoriaProdottoService(CategoriaProdottoRepository categoriaProdottoRepository) {
        this.categoriaProdottoRepository = categoriaProdottoRepository;
    }

    public void createCategoriaProdotto(String nome) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome) == null){
            categoria_prodotto cp = new categoria_prodotto();
            cp.setNome(nome);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    public void createCategoriaProdotto(String nome, String descrizione) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome) == null){
            categoria_prodotto cp = new categoria_prodotto();
            cp.setNome(nome);
            cp.setDescrizione(descrizione);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    @Transactional(readOnly = true)
    public List<categoria_prodotto> findCategoria(String nome){
        return categoriaProdottoRepository.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<categoria_prodotto> ordinaCategorie(){
       return categoriaProdottoRepository.findAllByOrderByNomeAsc();
    }
}
