package com.school_shuttle.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Responsavel;
import com.school_shuttle.back.model.Usuario;
import java.util.List;

public interface IResponsavel extends JpaRepository<Responsavel, Long> {

    public Optional<Responsavel> findById(Long id);

    public Optional<Responsavel> findByUser_Id(Long userId);

    public Optional<Responsavel> findByNomeCrianca(String nomeCrianca);
}
