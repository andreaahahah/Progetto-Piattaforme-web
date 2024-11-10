package com.example.stockhouse.services;

import com.example.stockhouse.entities.Categoria_prodotto;
import com.example.stockhouse.exceptions.CategoriaProdottoAlreadyExist;
import com.example.stockhouse.repositories.CategoriaProdottoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdottoService {

    private final CategoriaProdottoRepository categoriaProdottoRepository;

    public CategoriaProdottoService(CategoriaProdottoRepository categoriaProdottoRepository) {
        this.categoriaProdottoRepository = categoriaProdottoRepository;
    }

    public void createCategoriaProdotto(String nome) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome).isEmpty()){
            Categoria_prodotto cp = new Categoria_prodotto();
            cp.setNome(nome);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    public void createCategoriaProdotto(String nome, String descrizione) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome).isEmpty()){
            Categoria_prodotto cp = new Categoria_prodotto();
            cp.setNome(nome);
            cp.setDescrizione(descrizione);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    @Transactional(readOnly = true)
    public Optional<Categoria_prodotto> findCategoria(String nome){
        return categoriaProdottoRepository.findCategoriaProdottoByNome(nome);
    }

    @Transactional(readOnly = true)
    public List<Categoria_prodotto> getCategorie(){
       return categoriaProdottoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria_prodotto> getCategoria(int id){
        return categoriaProdottoRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public Optional<Categoria_prodotto> getCategoria(String nome){
        return categoriaProdottoRepository.findCategoriaProdottoByNome(nome);
    }

}
