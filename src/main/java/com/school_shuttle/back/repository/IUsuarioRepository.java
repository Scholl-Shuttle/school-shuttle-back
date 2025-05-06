package com.school_shuttle.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.school_shuttle.back.model.Motorista;
import com.school_shuttle.back.model.Responsavel;
import com.school_shuttle.back.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(@NonNull Long id);

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findUsuarioByMotorista(Motorista motorista);

    Optional<Usuario> findUsuarioByResponsavel(Responsavel responsavel);
}
