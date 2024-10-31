package com.example.stockhouse.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record ProdottoDTO(
        @NotNull
        int id,
        @NotNull
                @Min(1)
        int quantita


        ) {




}
