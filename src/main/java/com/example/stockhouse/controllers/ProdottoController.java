package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.services.MarcaService;
import com.example.stockhouse.services.ProdottoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prodotto")
public class ProdottoController {

    private final ProdottoService prodottoService;
    private final MarcaService marcaService;


    public ProdottoController(ProdottoService prodottoService, MarcaService marcaService) {
        this.prodottoService = prodottoService;
        this.marcaService = marcaService;


    }

    @GetMapping("elenca")
    public  ResponseEntity<?> getAllProdotti(
            @RequestParam (value = "page", defaultValue = "0")@NotNull int page ,
            @RequestParam(value ="s", defaultValue = "20") @NotNull int size,
            @RequestParam(value = "so", defaultValue = "id")@NotNull String sort
    ){
        return ResponseEntity.ok(prodottoService.showAllProducts(page,size,sort));
    }
    @GetMapping("getProdotto")
    public  ResponseEntity<?> getProd(
            @RequestParam ("prod")@NotNull int prod
    ){
        return ResponseEntity.ok(prodottoService.getProd(prod));
    }

    @GetMapping("getProdotti")
    public  ResponseEntity<?> getVProdotti(
            @RequestParam ("prod")@NotNull String prod
    ){
        return ResponseEntity.ok(prodottoService.showProductsByNameS(prod));
    }


    @GetMapping("elencaVetrina")
    public  ResponseEntity<?> getVetrina(){
        return ResponseEntity.ok(prodottoService.showVetrina());
    }


    @PostMapping("crea")
    public ResponseEntity<?> createProduct(
            @RequestParam("p")@NotNull int prezzo  ,
            @RequestParam("q")@NotNull Integer quantita,
            @RequestParam("m")@NotNull String brand,
            @RequestParam("n")@NotNull String nome,
            @RequestParam("d")@NotNull String descrizione,
            @RequestParam("img")@NotNull String immagini
    ){
        if(prezzo>0 && quantita>0 && !brand.isEmpty() && !nome.isEmpty() && !descrizione.isEmpty() && !immagini.isEmpty() && marcaService.existMarca(brand)) {
            Marca m = marcaService.findMarca(brand);
            prodottoService.createProdotto(nome, prezzo, descrizione, immagini, quantita, m);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("vetrina")
    public ResponseEntity<?> setVetrina(@RequestParam("prod") @NotNull int id){
        try {
            System.out.println(id);
            prodottoService.setVetrina(id);

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
