package com.example.stockhouse.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdottosDTO(
        @NotNull
        List< @Valid ProdottoDTO> prodottoDTOList
) {
}
