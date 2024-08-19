package com.example.stockhouse.services;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.repositories.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    private MarcaRepository marcaRepository;

    public List<Marca> findMarca(String nome){
        return marcaRepository.findByNomeContaining(nome);
    }

    public List<Marca> listMarca(){
        return marcaRepository.findAllByOrderByNomeAsc();
    }
}
