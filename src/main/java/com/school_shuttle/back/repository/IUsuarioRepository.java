package com.school_shuttle.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import com.school_shuttle.back.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findById(@NonNull Long id);

    public Optional<Usuario> findByNome(String nome);

    public Optional<Usuario> findByCpf(String cpf);

    public Optional<Usuario> findByTelefone(String telefone);

    public UserDetails findByEmail(String email);

    public Boolean existsByEmail(String email);

}
