package com.example.stockhouse.controllers;

import com.example.stockhouse.entities.Categoria_prodotto;
import com.example.stockhouse.entities.Marca;
import com.example.stockhouse.exceptions.ProdottoNotExist;
import com.example.stockhouse.services.CategoriaProdottoService;
import com.example.stockhouse.services.MarcaService;
import com.example.stockhouse.services.ProdCateService;
import com.example.stockhouse.services.ProdottoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prodotto")
public class ProdottoController {

    private final ProdottoService prodottoService;
    private final MarcaService marcaService;

    private  final CategoriaProdottoService categoriaProdottoService;
    private final ProdCateService prodCateService;


    public ProdottoController(ProdottoService prodottoService, MarcaService marcaService, CategoriaProdottoService categoriaProdottoService, ProdCateService prodCateService) {
        this.prodottoService = prodottoService;
        this.marcaService = marcaService;


        this.categoriaProdottoService = categoriaProdottoService;
        this.prodCateService = prodCateService;
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

    @GetMapping("elencaProdByMarca")
    public  ResponseEntity<?>getProdByMarca(
            @RequestParam ("marca")@NotNull String marca
    ){
        Marca m = marcaService.findMarca(marca);
        return ResponseEntity.ok(prodottoService.showProdByMarca(m));
    }


    @PostMapping("crea")
    public ResponseEntity<?> createProduct(
            @RequestParam("p")@NotNull int prezzo  ,
            @RequestParam("q")@NotNull Integer quantita,
            @RequestParam("m")@NotNull String brand,
            @RequestParam("n")@NotNull String nome,
            @RequestParam("d")@NotNull String descrizione,
            @RequestParam("img")@NotNull String immagini,
            @RequestParam("c")@NotNull String categoria,
            @RequestParam("v")@NotNull Boolean vetrina
    ) throws ProdottoNotExist {
        System.out.println("Prezzo: " + prezzo);
        System.out.println("Quantità: " + quantita);
        System.out.println("Brand: " + brand);
        System.out.println("Nome: " + nome);
        System.out.println("Descrizione: " + descrizione);
        System.out.println("Immagini: " + immagini);
        System.out.println("La marca esiste: " + marcaService.existMarca(brand));
        System.out.println("Categoria: " + categoria);
            if (prezzo > 0 && quantita > 0 && !brand.isEmpty() && !nome.isEmpty() && !descrizione.isEmpty() && !immagini.isEmpty() && marcaService.existMarca(brand) && !categoria.isEmpty()) {
                Marca m = marcaService.findMarca(brand);

                int prod = prodottoService.createProdotto(nome, prezzo, descrizione, immagini, quantita, m);
                String[] categorieArray = categoria.split(" ");

                // Associa ogni categoria al prodotto
                for (String cate : categorieArray) {
                    Optional<Categoria_prodotto> categoriaProdotto = categoriaProdottoService.getCategoria(cate);
                    if (categoriaProdotto.isPresent()) {
                        prodCateService.addCategoria(prod, categoriaProdotto.get().getId_categoria());
                    } else {
                        // Se una categoria non esiste, restituisce errore
                        System.out.println(cate);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria non valida: " + cate);
                    }
                }
                prodottoService.setVetrina(prod);
                return ResponseEntity.ok().build();
            } else {
                System.out.println(categoria);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

    @PostMapping("vetrina")
    public ResponseEntity<?> setVetrina(@RequestParam("prod") @NotNull int id){
        try {
            prodottoService.setVetrina(id);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
