package com.example.stockhouse.services;

import com.example.stockhouse.entities.marca;
import com.example.stockhouse.exceptions.MarcaAlreadyExist;
import com.example.stockhouse.repositories.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    public void createMarca(String nome) throws MarcaAlreadyExist {
        if(marcaRepository.findMarcaByNome(nome) == null){
            marca m = new marca();
            m.setNome(nome);
            marcaRepository.save(m);}
        else {
            throw  new MarcaAlreadyExist();
        }
    }
    @Transactional(readOnly = true)
    public List<marca> findMarcas(String nome){
        return marcaRepository.findMarcaByNomeContaining(nome);
    }
    @Transactional(readOnly = true)
    public List<marca> listMarca(){
        return marcaRepository.findAllByOrderByNomeAsc();

    }

    public marca findMarca(String nome){
        return  marcaRepository.findMarcaByNome(nome);
    }

    public boolean existMarca(String nome){
        return marcaRepository.existsByNome(nome);
    }
}
