package com.school_shuttle.back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
		@NotBlank(message = "O e-mail é obrigatório.")
		@Email(message = "Insira um e-mail válido.")
		String email,

		@NotBlank(message = "A senha é obrigatória.")
		String senha
) {
}
