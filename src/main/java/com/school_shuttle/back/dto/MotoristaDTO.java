package com.school_shuttle.back.dto;

import com.school_shuttle.back.model.Motorista;

public record MotoristaDTO(
        Long id,
        String placaDoVeiculo
) {
    public MotoristaDTO(Motorista motorista) {
        this(motorista.getId(), motorista.getPlacaVeiculo());
    }
}
