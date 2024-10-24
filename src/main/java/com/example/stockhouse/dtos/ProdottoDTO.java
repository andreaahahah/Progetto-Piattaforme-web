package com.example.stockhouse.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;


public record ProdottoDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        int id,

        @NotNull
        int prezzo,

        @NotNull
        int quantita,

        @NotNull
        String nome,

        @NotNull
        String descrizione,

        boolean vetrina,

        String nomeMarca,

        String immagini

        ) {




}
