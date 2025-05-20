package com.school_shuttle.back.model;

import org.hibernate.annotations.OnDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school_shuttle.back.dto.MotoristaDTO;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Motorista {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @NonNull

    private String placaVeiculo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Usuario usuario;

    public Motorista() {
    }

    public Motorista(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlacaVeiculo() {
        return this.placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void atualizarMotorista(MotoristaDTO json) {
        if (json.placaDoVeiculo() != null && !json.placaDoVeiculo().isBlank()) {
            this.placaVeiculo = json.placaDoVeiculo();
        }
    }
}
