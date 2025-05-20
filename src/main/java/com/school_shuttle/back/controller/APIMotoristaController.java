package com.school_shuttle.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school_shuttle.back.dto.MotoristaDTO;
import com.school_shuttle.back.model.Resposta;
import com.school_shuttle.back.service.IMotoristaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/motorista")
public class APIMotoristaController {

    @Autowired
    IMotoristaService motoristaService;

    @GetMapping
    public List<MotoristaDTO> consultaTodosMotoristas() {
        var motoristas = motoristaService.listaTodosMotoristas();

        return motoristas.stream().map(MotoristaDTO::new).toList();
    }

    @GetMapping("/usuario")
    public List<MotoristaDTO> consultaMotoristaPorUsuario(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var motoristas = motoristaService.consultaMotoristaPorUsuario((Long) userId);

        return motoristas.stream().map(MotoristaDTO::new).toList();
    }

    @GetMapping("/{id}")
    public MotoristaDTO consultaMotoristaPorId(@RequestParam Long id) {
        var motorista = motoristaService.consultaMotoristaPorId(id);

        return new MotoristaDTO(motorista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MotoristaDTO> cadastrarMotorista(@RequestBody MotoristaDTO json,
            UriComponentsBuilder uriBuilder) {
        var motorista = motoristaService.cadastrarMotorista(json);
        var uri = uriBuilder.path("/api/v1/motorista/{id}").buildAndExpand(motorista.getId()).toUri();

        return ResponseEntity.created(uri).body(new MotoristaDTO(motorista));
    }

    @PostMapping("/usuario")
    @Transactional
    public MotoristaDTO adicionarUsuario(@RequestBody MotoristaDTO json, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var motorista = motoristaService.adicionarUsuario((Long) userId, json);

        return new MotoristaDTO(motorista);
    }

    @PatchMapping("/usuario/desassociar")
    @Transactional
    public MotoristaDTO removerUsuario(@RequestBody MotoristaDTO json) {
        var motorista = motoristaService.removerUsuario(json);

        return new MotoristaDTO(motorista);
    }

    @PatchMapping("/{id}")
    @Transactional
    public MotoristaDTO atualizarMotorista(@PathVariable Long id, @RequestBody MotoristaDTO json) {
        var motorista = motoristaService.atualizarMotorista(json, id);

        return new MotoristaDTO(motorista);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletarMotorista(@PathVariable Long id, HttpServletRequest request) {
        motoristaService.deletarMotorista(id);

        Resposta resposta = new Resposta();

        resposta.setMensagem("Motorista deletado com sucesso");
        resposta.setStatus(HttpStatus.OK);
        resposta.setCaminho(request.getRequestURI().toString());
        resposta.setMetodo(request.getMethod());

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}