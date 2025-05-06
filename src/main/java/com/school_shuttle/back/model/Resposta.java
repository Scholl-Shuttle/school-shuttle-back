package com.school_shuttle.back.model;

import org.springframework.http.HttpStatus;

public class Resposta {
    private String erro;
    private String mensagem;
    private HttpStatus status;
    private String caminho;
    private String metodo;

    public Resposta() {

    }

    public Resposta(String erro, String mensagem, HttpStatus status, String caminho, String metodo) {
        this.erro = erro;
        this.mensagem = mensagem;
        this.status = status;
        this.caminho = caminho;
        this.metodo = metodo;
    }

    public String getErro() {
        return this.erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCaminho() {
        return this.caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getMetodo() {
        return this.metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
