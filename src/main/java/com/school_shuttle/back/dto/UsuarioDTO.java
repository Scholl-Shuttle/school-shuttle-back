package com.school_shuttle.back.dto;

import com.school_shuttle.back.model.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
		Long id,

		@NotBlank(message = "O nome é obrigatório.")
		String nome,

		@NotBlank(message = "O e-mail é obrigatório.")
		@Email(message = "Insira um e-mail válido.")
		String email,
		String senha
) {
	public UsuarioDTO(Usuario user) {
		this(user.getId(), user.getNome(), user.getEmail(), user.getSenha());
	}
}