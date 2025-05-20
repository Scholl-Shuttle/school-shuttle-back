package com.school_shuttle.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Motorista;

public interface IMotoristaRepository extends JpaRepository<Motorista, Long> {

    public Optional<Motorista> findById(Long id);

    public Optional<Motorista> findByUsuario_Id(Long usuarioId);

    public Optional<Motorista> findByPlacaVeiculo(String placaVeiculo);
}
