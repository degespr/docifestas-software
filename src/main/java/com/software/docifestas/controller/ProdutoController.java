package com.software.docifestas.controller;

import com.software.docifestas.model.Produto;
import com.software.docifestas.repository.ProdutoRepository;
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

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }

    @GetMapping("/mais-vendidos")
    public List<Object[]> rankingProdutos() {
        return produtoService.produtosMaisVendidos();
    }
}