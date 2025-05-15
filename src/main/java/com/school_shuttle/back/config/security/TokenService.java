package com.school_shuttle.back.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.school_shuttle.back.config.error.ErroCustomizado;
import com.school_shuttle.back.model.Usuario;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    /* Método para gerar um token JWT
     * withIssuer() -> Define a assinatura do JWT, qual servidor é dono dela. Se recebermos um JWT com assinatura diferente deve dar erro
     *
     * withSubject() -> Assinatura do usuário dono do JWT. Como usamos e-mail para login, coloco o e-mail dele aqui
     *
     * withClaim() -> Posso ter vários desses. É para retornar informações extras se eu quiser no JWT. Por exemplo, se o usuário tivesse
     * foto de perfil, pro front-end não precisar ficar pedindo toda hora o link dessa foto, eu posso retornar ele no JWT. Aqui coloquei
     * o id só para teste, mas provavelmente não é uma informação relevante de se retornar.
     *
     * WithExpiresAt() -> TOKENS GERALMENTE POSSUI VALIDADE. Aqui eu defini a validade de 7 dias a partir da emissão, usando o método
     * criado logo abaixo.
     *
     * sign() -> Para definir o tipo do JWT e qual sua chave secreta. No caso defini isso na variável algorithm e to chamando aqui.
     */

    private static final String TOKENSECRET = "VALOR_ALEATORIO_PRIVADO_SECRETO_PRA_DIFERENCIAR_NOSSO_BACK_END";
    private static final String RESTSIGNATURE = "API REST KEPOS";

    public String gerarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(TOKENSECRET);
            return JWT.create()
                    .withIssuer(RESTSIGNATURE)
                    .withSubject(usuario.getEmail())
                    .withClaim("userId", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new ErroCustomizado("Erro ao gerar token jwt");
        }
    }

    /* Método pra verificar se o token é valido e pertence mesmo a esse back-end.
     * Passo o algoritmo usado, a assinatura usada, o JWT SECRET usado e verifico se bate com o token que recebo por parâmetro
     * Se bater, eu retorno os dados do token (no caso o e-mail).
     * */
    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(TOKENSECRET);
            return JWT.require(algorithm)
                    .withIssuer(RESTSIGNATURE)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new ErroCustomizado("Token JWT inválido ou expirado!");
        }
    }

    public Long getUserId(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(TOKENSECRET);
            return JWT.require(algorithm)
                    .withIssuer(RESTSIGNATURE)
                    .build()
                    .verify(tokenJWT)
                    .getClaim("userId")
                    .asLong();
        } catch (JWTVerificationException exception) {
            throw new ErroCustomizado("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }

}