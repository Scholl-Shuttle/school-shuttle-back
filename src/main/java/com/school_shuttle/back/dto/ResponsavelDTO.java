package com.school_shuttle.back.dto;

import com.school_shuttle.back.model.Responsavel;

public record ResponsavelDTO(
        Long id,
        String nomeCrianca,
        String endereco
        ) {
    public ResponsavelDTO(Responsavel responsavel) {
        this(responsavel.getId(), responsavel.getNomeCrianca(), responsavel.getEndereco());
    }
}
