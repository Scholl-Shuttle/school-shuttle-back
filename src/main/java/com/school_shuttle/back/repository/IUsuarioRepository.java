package com.school_shuttle.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.school_shuttle.back.model.Motorista;
import com.school_shuttle.back.model.Responsavel;
import com.school_shuttle.back.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findById(@NonNull Long id);

    public Optional<Usuario> findByNome(String nome);

    public Optional<Usuario> findByCpf(String cpf);

    public Optional<Usuario> findByTelefone(String telefone);

    public Optional<Usuario> findUsuarioByMotorista(Motorista motorista);

    public Optional<Usuario> findUsuarioByResponsavel(Responsavel responsavel);
}
