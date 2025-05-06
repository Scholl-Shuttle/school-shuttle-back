package com.school_shuttle.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Responsavel;

import java.util.List;

public interface IResponsavelRepository extends JpaRepository<Responsavel, Long> {

    public Optional<Responsavel> findById(Long id);

    public List<Responsavel> findByUser_Id(Long userId);

    public Optional<Responsavel> findByNomeCrianca(String nomeCrianca);
}
