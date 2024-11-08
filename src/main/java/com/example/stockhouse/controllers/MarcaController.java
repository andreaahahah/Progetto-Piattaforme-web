package com.example.stockhouse.controllers;

import com.example.stockhouse.repositories.MarcaRepository;
import com.example.stockhouse.services.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("marca")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping("crea")
    public ResponseEntity<?> crateMarca(
            @RequestParam("nome") String nome
    ){
        //TODO verifica che possa aggiungere cioè deve avere i permessi
        if(nome != null && !nome.isEmpty() && !nome.equals(" ")) {
            try {
                marcaService.createMarca(nome);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("elenca")
    public ResponseEntity<?>getAllMarca(){
        return ResponseEntity.ok(marcaService.listMarca());
    }

    @GetMapping("trova")
    public ResponseEntity<?>getAllMarca(@RequestParam("nome") String nome){
        return ResponseEntity.ok(marcaService.findMarcas(nome));
    }


}
