package com.software.docifestas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService myBrain;

    @PostMapping
    public Usuario criarCadastro(@RequestBody Usuario usuario) {
        return myBrain.salvar(usuario);
    }
}