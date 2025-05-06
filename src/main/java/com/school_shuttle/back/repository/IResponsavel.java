package com.school_shuttle.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Responsavel;

public interface IResponsavel extends JpaRepository<Responsavel, Long> {

    Optional<Responsavel> findByEmail(String email);

    Optional<Responsavel> findById(Long id);

    Optional<Responsavel> findByNome(String nome);

    Optional<Responsavel> findByTelefone(String telefone);

    Optional<Responsavel> findByCpf(String cpf);

}
