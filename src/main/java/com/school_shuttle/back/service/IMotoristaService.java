package com.school_shuttle.back.service;

import java.util.List;
import java.util.Optional;

import com.school_shuttle.back.dto.MotoristaDTO;
import com.school_shuttle.back.model.Motorista;

public interface IMotoristaService {

    public List<Motorista> listaTodosMotoristas();

    public Motorista consultaMotoristaPorPlaca(String placaDoVeiculo);

    public Motorista consultaMotoristaPorId(Long idMotorista);

    public Optional<Motorista> consultaMotoristaPorUsuario(Long idUsuario);

    public Motorista cadastrarMotorista(MotoristaDTO json);

    public Motorista atualizarMotorista(MotoristaDTO json, Long idMotorista);

    public void deletarMotorista(Long idMotorista);

    public Motorista adicionarUsuario(Long userId, MotoristaDTO json);

    public Motorista removerUsuario(MotoristaDTO json);
}
