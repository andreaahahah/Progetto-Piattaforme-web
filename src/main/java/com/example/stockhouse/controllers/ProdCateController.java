package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Categoria_prodotto;
import com.example.stockhouse.entities.Prodotto;
import com.example.stockhouse.services.CategoriaProdottoService;
import com.example.stockhouse.services.ProdCateService;
import com.example.stockhouse.services.ProdottoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("prodottoCategoria")
public class ProdCateController {

    private final ProdCateService prodCateService;
    private final CategoriaProdottoService categoriaProdottoService;

    private final ProdottoService  prodottoService;
    public ProdCateController(ProdCateService prodCateService, CategoriaProdottoService categoriaProdottoService, ProdottoService prodottoService) {
        this.prodCateService = prodCateService;
        this.categoriaProdottoService = categoriaProdottoService;
        this.prodottoService = prodottoService;
    }

    @GetMapping("elenca")
    public ResponseEntity<?> getAllCategorie(){
        return ResponseEntity.ok(categoriaProdottoService.getCategorie());
    }

    @GetMapping("getCategorie")
    public ResponseEntity<?> getCategorie(
            @RequestParam(value = "prod")@NotNull int prodotto
    ){
        Optional<Prodotto> p = prodottoService.getProd(prodotto);
        if(p.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prodCateService.findCategorie(p.get()));
    }

    @GetMapping("getProdotti")
    public ResponseEntity<?> getProdotti(
            @RequestParam(value = "cate")@NotNull int categoria
    ){
        Optional<Categoria_prodotto> cp = categoriaProdottoService.getCategoria(categoria);
        if(cp.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prodCateService.findProdotti(cp.get()));
    }

    @PostMapping("creaCategoria")
    public  ResponseEntity<?> creaCategoria(
            @RequestParam(value = "nome")@NotNull String nome,
            @RequestParam(value = "des") String descrizione
    ){
        try{
            if(descrizione.isBlank()){
            categoriaProdottoService.createCategoriaProdotto(nome);}
            else{
                categoriaProdottoService.createCategoriaProdotto(nome,descrizione);
            }
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("aggiungiCategoria")
    public ResponseEntity<?> addCategoria(
            @RequestParam(value = "prod")@NotNull int prodotto,
            @RequestParam(value = "cate")@NotNull int categoria
    ){
        //controlli già fatti nel service
        if(!prodCateService.addCategoria(prodotto, categoria)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();

    }

}
