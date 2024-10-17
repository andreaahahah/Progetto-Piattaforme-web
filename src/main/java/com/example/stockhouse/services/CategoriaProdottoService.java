package com.example.stockhouse.services;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.exceptions.CategoriaProdottoAlreadyExist;
import com.example.stockhouse.repositories.CategoriaProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProdottoService {

    @Autowired
    private CategoriaProdottoRepository categoriaProdottoRepository;

    public void createCategoriaProdotto(String nome) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome) == null){
            CategoriaProdotto cp = new CategoriaProdotto();
            cp.setNome(nome);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    public void createCategoriaProdotto(String nome, String descrizione) throws CategoriaProdottoAlreadyExist {
        if(categoriaProdottoRepository.findCategoriaProdottoByNome(nome) == null){
            CategoriaProdotto cp = new CategoriaProdotto();
            cp.setNome(nome);
            cp.setDescrizione(descrizione);
            categoriaProdottoRepository.save(cp);
        }
        else{
            throw new CategoriaProdottoAlreadyExist();
        }
    }
    @Transactional(readOnly = true)
    public List<CategoriaProdotto> findCategoria(String nome){
        return categoriaProdottoRepository.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<CategoriaProdotto> ordinaCategorie(){
       return categoriaProdottoRepository.findAllByOrderByNomeAsc();
    }
}
