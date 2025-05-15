package com.school_shuttle.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school_shuttle.back.config.security.DadosTokenJWT;
import com.school_shuttle.back.dto.LoginDTO;
import com.school_shuttle.back.dto.UsuarioDTO;
import com.school_shuttle.back.service.IUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
public class APIUsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping
    public UsuarioDTO listaUsuarioLogado(HttpServletRequest request) {
        var idUsuario = request.getAttribute("userId");
        var usuario = usuarioService.listaUsuarioLogado((Long) idUsuario);

        return new UsuarioDTO(usuario);
    }

    @GetMapping("/all")
    @Transactional
    public List<UsuarioDTO> listaTodosUsuarios() {
        var usuarios = usuarioService.listaTodosUsuarios();

        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO json, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrarUsuario(json);

        var uri = uriBuilder.path("/api/v1/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PostMapping("/login")
    @Transactional
    public DadosTokenJWT efetuarLogin(@RequestBody @Valid LoginDTO json) {
        var token = usuarioService.realizarLogin(json);

        return new DadosTokenJWT(token);
    }

    @PutMapping
    @Transactional
    public DadosTokenJWT atualizarUsuario(HttpServletRequest request, @RequestBody @Valid UsuarioDTO json) {
        var idUsuario = request.getAttribute("userId");
        var token = usuarioService.atualizarUsuario(json, (Long) idUsuario);

        request.setAttribute("Authorization", "Bearer " + token);
        return new DadosTokenJWT(token);
    }

    @DeleteMapping
    @Transactional
    public void deletarUsuario(HttpServletRequest request) {
        var idUsuario = request.getAttribute("userId");
        usuarioService.deletarUsuario((Long) idUsuario);
    }
}
