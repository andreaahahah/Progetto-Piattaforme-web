package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.carrello;
import com.example.stockhouse.entities.dettaglio_carrello;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioCarrelloRepository extends JpaRepository<dettaglio_carrello, Integer> {

    List<dettaglio_carrello> findByIdCarrello(carrello carrello);

    Boolean existsByIdCarrelloAndAndIdProdotto(carrello carrello, Prodotto prodotto);
    dettaglio_carrello findByIdCarrelloAndIdProdotto(carrello carrello, Prodotto prodotto);

    @Query("SELECT dc.idProdotto " +
            "FROM dettaglio_carrello dc "+
            "WHERE dc = ?1 "
    )
    Prodotto findProdottoByIdDettaglio(int id);


}
