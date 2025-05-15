package com.school_shuttle.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school_shuttle.back.dto.ResponsavelDTO;
import com.school_shuttle.back.model.Resposta;
import com.school_shuttle.back.service.IResponsavelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/responsavel")
public class APIResponsavelController {

    @Autowired
    IResponsavelService responsavelService;

    @GetMapping
    public List<ResponsavelDTO> consultaTodosResponsaveis() {
        var responsaveis = responsavelService.listaTodosResponsaveis();

        return responsaveis.stream().map(ResponsavelDTO::new).toList();
    }

    @GetMapping("/usuario")
    public List<ResponsavelDTO> consultaResponsavelPorUsuario(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var responsaveis = responsavelService.consultaResponsavelUsuario((Long) userId);

        return responsaveis.stream().map(ResponsavelDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponsavelDTO consultaResponsavelPorId(@RequestParam Long id) {
        var responsavel = responsavelService.consultaResponsavelPorId(id);

        return new ResponsavelDTO(responsavel);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsavelDTO> cadastrarResponsavel(@RequestBody ResponsavelDTO json,
            UriComponentsBuilder uriBuilder) {
        var responsavel = responsavelService.cadastrarResponsavel(json);
        var uri = uriBuilder.path("/api/v1/responsavel/{id}").buildAndExpand(responsavel.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponsavelDTO(responsavel));
    }

    @PostMapping("/usuario")
    @Transactional
    public ResponsavelDTO adicionarUsuario(@RequestBody ResponsavelDTO json, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var responsavel = responsavelService.adicionarUsuario((Long) userId, json);

        return new ResponsavelDTO(responsavel);
    }

    @PatchMapping("/usuario/desassociar")
    @Transactional
    public ResponsavelDTO removerUsuario(@RequestBody ResponsavelDTO json) {
        var responsavel = responsavelService.removerUsuarioResponsavel(json.id());
        return new ResponsavelDTO(responsavel);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponsavelDTO atualizarResponsavel(@PathVariable Long id, @RequestBody ResponsavelDTO json) {
        var responsavel = responsavelService.atualizarResponsavel(json, id);
        return new ResponsavelDTO(responsavel);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletarResponsavel(@PathVariable Long id, HttpServletRequest request) {
        responsavelService.deletarResponsavel(id);

        Resposta resposta = new Resposta();

        resposta.setMensagem("Responsavel deletado com sucesso");
        resposta.setStatus(HttpStatus.OK);
        resposta.setCaminho(request.getRequestURI().toString());
        resposta.setMetodo(request.getMethod());

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
