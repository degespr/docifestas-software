package com.software.docifestas.controller;

import com.software.docifestas.dto.produto.ProdutoRequestDTO;
import com.software.docifestas.service.ProdutoService;
import com.software.docifestas.dto.produto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PostMapping
    @Operation(summary = "Cria os produtos no sistema.")
    public ProdutoResponseDTO criarProduto(@RequestBody ProdutoRequestDTO request) {return produtoService.salvar(request);}

    @Operation(summary = "Lista os produtos disponíveis no estoque.")
    @GetMapping
    public List<ProdutoResponseDTO> listarTudo() {
        return produtoService.listarTodos();
    }

    @Operation(summary = "Realizar busca de acordo com o ID do produto.")
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar dados de um produto existente.")
    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO request) {
        return produtoService.atualizarProduto(id, request);
    }

    @Operation(summary = "Remove produtos do sistema.")
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }
}