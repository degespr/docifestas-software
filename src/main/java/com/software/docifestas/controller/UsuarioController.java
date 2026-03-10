package com.software.docifestas.controller;

import com.software.docifestas.model.Venda;
import com.software.docifestas.service.VendaService;
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

    @Autowired
    private VendaService vendaService;

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

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
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