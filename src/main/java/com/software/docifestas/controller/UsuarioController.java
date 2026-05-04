package com.software.docifestas.controller;

import com.software.docifestas.dto.usuario.UsuarioRequestDTO;
import com.software.docifestas.dto.usuario.UsuarioResponseDTO;
import com.software.docifestas.model.Venda;
import com.software.docifestas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.software.docifestas.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public UsuarioResponseDTO criarCadastro(@RequestBody UsuarioRequestDTO request) {
        return usuarioService.salvar(request);

    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTudo() {
        return usuarioService.listarUsuarios();

    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        return usuarioService.atualizarUsuario(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/{id}/vendas")
    public List<Venda> listarVendasDoUsuario(@PathVariable Long id) {
        return vendaService.listarVendasDoUsuario(id);
    }
}