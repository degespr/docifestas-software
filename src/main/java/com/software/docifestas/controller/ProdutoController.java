package com.software.docifestas.controller;

import com.software.docifestas.model.Produto;
import com.software.docifestas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {return produtoService.salvar(produto);}

    @GetMapping
    public List<Produto> listarTudo() {
        return produtoService.listarTodos();
    }
}
