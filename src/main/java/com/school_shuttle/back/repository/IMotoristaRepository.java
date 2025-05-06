package com.school_shuttle.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_shuttle.back.model.Motorista;
import com.school_shuttle.back.model.Usuario;

public interface IMotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByEmail(String email);

    Optional<Motorista> findById(Long id);

    Optional<Motorista> findByNome(String nome);

    List<Motorista> findMotoristaByUsuario(Usuario usuario);
}
