package com.example.stockhouse.services;

import com.example.stockhouse.entities.CategoriaProdotto;
import com.example.stockhouse.repositories.CategoriaProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProdottoService {

    @Autowired
    private CategoriaProdottoRepository categoriaProdottoRepository;

    @Transactional(readOnly = true)
    public List<CategoriaProdotto> findCategoria(String nome){
        return categoriaProdottoRepository.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<CategoriaProdotto> ordinaCategorie(){
       return categoriaProdottoRepository.findAllByOrderByNomeAsc();
    }
}
