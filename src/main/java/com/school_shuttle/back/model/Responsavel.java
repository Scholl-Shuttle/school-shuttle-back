package com.school_shuttle.back.model;

import org.hibernate.annotations.OnDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school_shuttle.back.dto.ResponsavelDTO;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Responsavel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String nomeCrianca;
    @NonNull
    private String endereco;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Usuario usuario;

    public Responsavel() {
    }

    public Responsavel(String endereco, String nomeCrianca) {
        this.endereco = endereco;
        this.nomeCrianca = nomeCrianca;

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeCrianca() {
        return this.nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void atualizarResponsavel(ResponsavelDTO json) {
        if (json.nomeCrianca() != null) {
            this.nomeCrianca = json.nomeCrianca();
        }
        if (json.endereco() != null) {
            this.endereco = json.endereco();
        }
    }
}
