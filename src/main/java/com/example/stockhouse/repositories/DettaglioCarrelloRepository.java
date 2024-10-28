package com.example.stockhouse.repositories;

import com.example.stockhouse.entities.Carrello;
import com.example.stockhouse.entities.Dettaglio_carrello;
import com.example.stockhouse.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioCarrelloRepository extends JpaRepository<Dettaglio_carrello, Integer> {

    List<Dettaglio_carrello> findByIdCarrello(Carrello carrello);

    Boolean existsByIdCarrelloAndAndIdProdotto(Carrello carrello, Prodotto prodotto);
    Dettaglio_carrello findByIdCarrelloAndIdProdotto(Carrello carrello, Prodotto prodotto);

    @Query("SELECT dc.idProdotto " +
            "FROM Dettaglio_carrello  dc "+
            "WHERE dc = ?1 "
    )
    Prodotto findProdottoByIdDettaglio(int id);


}
