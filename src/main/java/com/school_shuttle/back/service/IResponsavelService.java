package com.school_shuttle.back.service;

import java.util.List;
import java.util.Optional;

import com.school_shuttle.back.dto.ResponsavelDTO;
import com.school_shuttle.back.model.Responsavel;

public interface IResponsavelService {
    public List<Responsavel> listaTodosResponsaveis();

    public Optional<Responsavel> consultaResponsavelUsuario(Long idUsuario);

    public Responsavel consultaResponsavelPorId(Long idResponsavel);

    public Responsavel cadastrarResponsavel(ResponsavelDTO json);

    public Responsavel adicionarUsuario(Long idResponsavel, ResponsavelDTO json);

    public Responsavel atualizarResponsavel(ResponsavelDTO json, Long idResponsavel);

    public void deletarResponsavel(Long idResponsavel);

    public Responsavel removerUsuarioResponsavel(Long idResponsavel);
}
