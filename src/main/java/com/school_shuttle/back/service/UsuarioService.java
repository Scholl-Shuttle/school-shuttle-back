package com.school_shuttle.back.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school_shuttle.back.config.error.ErroCustomizado;
import com.school_shuttle.back.config.security.TokenService;
import com.school_shuttle.back.dto.LoginDTO;
import com.school_shuttle.back.dto.UsuarioDTO;
import com.school_shuttle.back.model.Usuario;
import com.school_shuttle.back.repository.IUsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    IUsuarioRepository usuarioRepository;
/* Injetando classe para criptografar senha */

    @Autowired
    private PasswordEncoder passwordEncoder;

/* Injetando classe para realizar login e autenticação */

    @Autowired
    private AuthenticationManager authenticationManager;

/* Injetando serviço de geração de token jwt para autenticação */

    @Autowired
    private TokenService tokenService;

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public Usuario listaUsuarioLogado(Long id) {
        
        logger.info("Listando usuarios pelo token");
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new EntityNotFoundException();
        }
        return usuario.get();
    }

    @Override
    public List<Usuario> listaTodosUsuarios() {
        logger.info("Listando  todos os usuarios no sistema"){
            return usuarioRepository.findAll();
        }
    }

    @Override
    public Usuario cadastrarUsuario(UsuarioDTO json) {
        logger.info("cadastrar usuario");
        if (usuarioRepository.existsByEmail(json.email())){
            throw new ErroCustomizado("E-mail já cadastrado no sistema");
        }
        var novoUsuario = new Usuario(json.cpf(),json.telefone(),json.nome(),json.email(),passwordEncoder.encode(json.senha()));
        return usuarioRepository.save(novoUsuario);
    }

    @Override
    public String realizarLogin(LoginDTO json) {
        try{
            var token = new UsernamePasswordAuthenticationToken(json.email(), json.senha());
            var authentication = manager.authenticate(token);
            return tokenService.gerarToken((Usuario) authentication.getPrincipal());]

        } catch(AuthenticationException e){
            throw new ErroCustomizado("E-mail ou senha inválidos.");
        }
        
    }

    @Override
    public String atualizarUsuario(UsuarioDTO json, Long id) {
        if (!usuarioRepository.existsById(id)){
            throw new EntityNotFoundException();
    }
        var usuario = usuarioRepository.findById(id);
        usuario.get().atualizarUsuario(json);

        if(json.senha()!=null){

        usuario.get().setSenha(passwordEncoder.encode(json.senha()));
        }
        return tokenService.gerarToken(usuario.get());

    }
    
    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository,deleteById(id);
    }

    
}
