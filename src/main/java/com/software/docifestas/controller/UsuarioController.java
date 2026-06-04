package com.software.docifestas.controller;

import com.software.docifestas.dto.usuario.UsuarioRequestDTO;
import com.software.docifestas.dto.usuario.UsuarioResponseDTO;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.software.docifestas.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // Atributos By Dege
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VendaService vendaService;

    // Codes For Aplications - POST
    @Operation(summary = "Cria o cadastro.")
    @PostMapping
    public UsuarioResponseDTO criarCadastro(@RequestBody UsuarioRequestDTO request) {
        return usuarioService.salvar(request);

    }

    // Codes For Aplications - GET
    @Operation(summary = "Lista todos os usuários.")
    @GetMapping
    public List<UsuarioResponseDTO> listarTudo() {
        return usuarioService.listarUsuarios();

    }

    @Operation(summary = "Busca um usuário pelo ID.")
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // Codes For Aplications - PUT
    @Operation(summary = "Atualiza um usuário.")
    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO request, @AuthenticationPrincipal Usuario usuario) {
        return usuarioService.atualizarUsuario(id, request, usuario);
    }

    // Codes For Aplications - DELETE
    @Operation(summary = "Deleta o perfil do usuário.")
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        usuarioService.deletarUsuario(id, usuario);
    }
}