package com.software.docifestas.controller;

import com.software.docifestas.model.Produto;
import com.software.docifestas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService myBrain;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {return myBrain.salvar(produto);}
}
