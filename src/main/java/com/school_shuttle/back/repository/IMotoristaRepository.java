package com.school_shuttle.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Motorista;
import com.school_shuttle.back.model.Usuario;

public interface IMotoristaRepository extends JpaRepository<Motorista, Long> {

    public Optional<Motorista> findById(Long id);

    public List<Motorista> findByUser_Id(Long userId);

    public Optional<Motorista> findByPlacaVeiculo(String placaVeiculo);
}
