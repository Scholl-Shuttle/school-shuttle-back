package com.school_shuttle.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Responsavel;

public interface IResponsavelRepository extends JpaRepository<Responsavel, Long> {

    public Optional<Responsavel> findById(Long id);

    public Optional<Responsavel> findByUsuario_Id(Long usuarioId);

    public Optional<Responsavel> findByNomeCrianca(String nomeCrianca);
}
