package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.DettaglioCarrello;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioCarrelloRepository extends JpaRepository<DettaglioCarrello, Integer> {

    List<DettaglioCarrello> findByCarrello(Carrello carrello);

    DettaglioCarrello findByCarrelloAndProdotto(Carrello carrello, Prodotto prodotto);

    @Query("SELECT dc.idProdotto " +
            "FROM DettaglioCarrello dc "+
            "WHERE dc = ?1 "
    )
    Prodotto findProdottoByIdDettaglio(int id);


}
