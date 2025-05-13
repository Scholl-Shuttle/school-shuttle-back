package com.school_shuttle.back.service;

import java.util.List;

import com.school_shuttle.back.dto.LoginDTO;
import com.school_shuttle.back.dto.UsuarioDTO;
import com.school_shuttle.back.model.Usuario;

public interface IUsuarioService {
    public Usuario listaUsuarioLogado(Long id);

    public List<Usuario> listaTodosUsuarios();

    public Usuario cadastrarUsuario(UsuarioDTO json);

    public String realizarLogin(LoginDTO json);

    public String atualizarUsuario(UsuarioDTO json, Long id);

    public String deletarUsuario(Long id);
}
