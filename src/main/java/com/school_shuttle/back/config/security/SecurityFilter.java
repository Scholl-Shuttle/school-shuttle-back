package com.school_shuttle.back.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school_shuttle.back.repository.IUsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IUsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenJWT = recuperarToken(request); /* Puxando o token */

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT); // Puxando os dados do token (Email)
            var usuarioOptional = repository.findByEmail(subject); // Puxando o usuário do banco pelo e-mail

            if (usuarioOptional.isPresent()) {
                var usuario = usuarioOptional.get();
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication); // Forçando autenticação
                request.setAttribute("userId", tokenService.getUserId(tokenJWT)); // Passando o id do cliente do token pra frente
            }
        }

        filterChain.doFilter(request, response); // Dando continuidade a cadeia de filtros
    }

    // Método que puxa a header Authorization e verifica se existe um Bearer token anexado, retornado ele caso exista */
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

}