package com.school_shuttle.back.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.config.error.ErroCustomizado;
import com.school_shuttle.back.dto.ResponsavelDTO;
import com.school_shuttle.back.model.Responsavel;
import com.school_shuttle.back.repository.IResponsavelRepository;
import com.school_shuttle.back.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResponsavelService implements IResponsavelService {

    @Autowired
    private IResponsavelRepository responsavelRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    Logger logger = LogManager.getLogger(getClass());

    public List<Responsavel> listaTodosResponsaveis() {
        logger.info("Service > List all responsaveis");

        return responsavelRepository.findAll();
    }

    public Optional<Responsavel> consultaResponsavelUsuario(Long idUsuario) {
        logger.info("Service > List responsavel by idUsuario");

        return responsavelRepository.findByUsuario_Id(idUsuario);
    }

    public Responsavel consultaResponsavelPorId(Long idResponsavel) {
        logger.info("Service > List responsavel by idResponsavel");

        var responsavel = responsavelRepository.findById(idResponsavel);

        if (responsavel.isEmpty()) {
            throw new EntityNotFoundException("Responsavel not found");
        }

        return responsavel.get();
    }

    public Responsavel cadastrarResponsavel(ResponsavelDTO json) {
        logger.info("Service > Create responsavel");

        var responsavel = new Responsavel(json.endereco(), json.nomeCrianca());

        return responsavelRepository.save(responsavel);
    }

    public Responsavel adicionarUsuario(Long idResponsavel, Long idUsuario) {
        logger.info("Service > Add usuario to responsavel");

        var responsavel = responsavelRepository.findById(idResponsavel);

        if (responsavel.isEmpty()) {
            logger.error("Responsavel not found:", idResponsavel);
            throw new EntityNotFoundException("Responsavel not found");
        }

        if (responsavel.get().getUsuario() != null) {
            throw new ErroCustomizado("Responsavel already has a user");
        }

        var usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        responsavel.get().setUsuario(usuario.get());

        return responsavel.get();
    }

    public Responsavel atualizarResponsavel(ResponsavelDTO json, Long idResponsavel) {
        logger.info("Service > Update responsavel");

        var responsavel = responsavelRepository.findById(idResponsavel);

        if (responsavel.isEmpty()) {
            throw new EntityNotFoundException("Responsavel not found");
        }

        responsavel.get().atualizarResponsavel(json);

        return responsavel.get();
    }

    public void deletarResponsavel(Long idResponsavel) {
        logger.info("Service > Delete responsavel");

        responsavelRepository.deleteById(idResponsavel);
    }

    public Responsavel removerUsuarioResponsavel(Long idResponsavel) {
        logger.info("Service > Remove usuario from responsavel");

        var responsavel = responsavelRepository.findById(idResponsavel);

        if (responsavel.isEmpty()) {
            throw new EntityNotFoundException("Responsavel not found");
        }

        if (responsavel.get().getUsuario() == null) {
            throw new EntityNotFoundException("Responsavel does not have a user");
        }

        responsavel.get().setUsuario(null);

        return responsavel.get();
    }

}
