package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Ordine;
import com.example.stockhouse.entities.Prod_ordinati;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdOrdinatiRepository extends JpaRepository<Prod_ordinati, Integer> {

    List<Prod_ordinati> findProd_ordinatisByOrdine(Ordine ordine);

}
