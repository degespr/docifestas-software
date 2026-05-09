package com.software.docifestas.controller;

import com.software.docifestas.dto.produto.ProdutoRequestDTO;
import com.software.docifestas.model.Produto;
import com.software.docifestas.service.ProdutoService;
import com.software.docifestas.dto.produto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseDTO criarProduto(@RequestBody ProdutoRequestDTO request) {return produtoService.salvar(request);}

    @GetMapping
    public List<ProdutoResponseDTO> listarTudo() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO request) {
        return produtoService.atualizarProduto(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }
}