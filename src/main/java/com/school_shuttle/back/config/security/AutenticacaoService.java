package com.school_shuttle.back.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.repository.IUsuarioRepository;

/* Implementando classe necessária para configurar autenticação */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    /* Método para autenticar o usuário, buscando os dados do banco de dados */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

}