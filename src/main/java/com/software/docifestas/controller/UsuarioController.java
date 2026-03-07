package com.software.docifestas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario criarCadastro(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);

    }

    @GetMapping
    public List<Usuario> listarTudo() {
        return usuarioService.listarUsuarios();

    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
}