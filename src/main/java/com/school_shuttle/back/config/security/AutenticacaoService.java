package com.school_shuttle.back.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.repository.IUsuarioRepository;
import org.springframework.security.core.userdetails.User;

/* Implementando classe necessária para configurar autenticação */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    /* Método para autenticar o usuário, buscando os dados do banco de dados */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(usuario -> new org.springframework.security.core.userdetails.User(
                        usuario.getEmail(),
                        usuario.getSenha(),
                        usuario.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}