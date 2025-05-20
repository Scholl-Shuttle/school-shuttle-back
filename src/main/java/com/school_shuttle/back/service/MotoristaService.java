package com.school_shuttle.back.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.config.error.ErroCustomizado;
import com.school_shuttle.back.dto.MotoristaDTO;
import com.school_shuttle.back.model.Motorista;
import com.school_shuttle.back.repository.IMotoristaRepository;
import com.school_shuttle.back.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MotoristaService implements IMotoristaService {

    @Autowired
    IMotoristaRepository motoristaRepository;

    @Autowired
    IUsuarioRepository usuarioRepository;

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public List<Motorista> listaTodosMotoristas() {
        logger.info("Service > List all motoristas");

        return motoristaRepository.findAll();
    }

    @Override
    public Motorista consultaMotoristaPorPlaca(String placaDoVeiculo) {
        logger.info("Service > List motorista by placa");

        var motorista = motoristaRepository.findByPlacaVeiculo(placaDoVeiculo);

        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        return motorista.get();
    }

    @Override
    public Optional<Motorista> consultaMotoristaPorUsuario(Long idUsuario) {
        logger.info("Service > List motorista by idUsuario");

        return motoristaRepository.findByUsuario_Id(idUsuario);
    }

    @Override
    public Motorista consultaMotoristaPorId(Long idMotorista) {
        logger.info("Service > List motorista by id");

        var motorista = motoristaRepository.findById(idMotorista);
        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        return motorista.get();
    }

    @Override
    public Motorista cadastrarMotorista(MotoristaDTO json) {
        logger.info("Service > Create motorista");

        if (json.id() == null) {
            throw new ErroCustomizado("Id is null");
        }

        var motorista = new Motorista(json.placaDoVeiculo());

        return motoristaRepository.save(motorista);
    }

    @Override
    public Motorista atualizarMotorista(MotoristaDTO json, Long idMotorista) {
        logger.info("Service > Update motorista");

        var motorista = motoristaRepository.findById(idMotorista);
        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        motorista.get().atualizarMotorista(json);

        return motorista.get();
    }

    @Override
    public void deletarMotorista(Long idMotorista) {
        logger.info("Service > Delete motorista");

        var motorista = motoristaRepository.findById(idMotorista);
        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        motoristaRepository.deleteById(idMotorista);
    }

    @Override
    public Motorista adicionarUsuario(Long userId, MotoristaDTO json) {
        logger.info("Service > Add usuario to motorista");

        if (json.id() == null) {
            throw new ErroCustomizado("Id of Motorista is null");
        }

        var motorista = motoristaRepository.findById(json.id());

        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        if (motorista.get().getUsuario() != null) {
            throw new ErroCustomizado("Motorista already has a user");
        }

        var usuario = usuarioRepository.findById(userId);

        if (usuario.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        motorista.get().setUsuario(usuario.get());
        return motorista.get();
    }

    @Override
    public Motorista removerUsuario(MotoristaDTO json) {
        logger.info("Service > Remove usuario from motorista");

        var motorista = motoristaRepository.findById(json.id());

        if (motorista.isEmpty()) {
            throw new EntityNotFoundException("Motorista not found");
        }

        motorista.get().setUsuario(null);

        return motorista.get();
    }

}
