package com.school_shuttle.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.config.security.TokenService;
import com.school_shuttle.back.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    IUsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
}
