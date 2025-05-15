package com.school_shuttle.back.dto;

public record MotoristaDTO(
        Long id,
        String placaDoVeiculo
) {
    public MotoristaDTO {
        if (id == null) {
            throw new IllegalArgumentException("O id é obrigatório.");
        }
        if (placaDoVeiculo == null || placaDoVeiculo.isBlank()) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
    }
}
