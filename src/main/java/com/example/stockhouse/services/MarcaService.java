package com.example.stockhouse.services;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.exceptions.MarcaAlreadyExist;
import com.example.stockhouse.repositories.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    public void createMarca(String nome) throws MarcaAlreadyExist {
        if(marcaRepository.findMarcaByNome(nome) == null){
            Marca m = new Marca();
            m.setNome(nome);
            marcaRepository.save(m);}
        else {
            throw  new MarcaAlreadyExist();
        }
    }
    @Transactional(readOnly = true)
    public List<Marca> findMarcas(String nome){
        return marcaRepository.findMarcaByNomeContaining(nome);
    }
    @Transactional(readOnly = true)
    public List<Marca> listMarca(){
        return marcaRepository.findAllByOrderByNomeAsc();

    }

    public Optional<Marca> findMarca(String nome){
        return  marcaRepository.findMarcaByNome(nome);
    }

    public boolean existMarca(String nome){
        return marcaRepository.existsByNome(nome);
    }


}
