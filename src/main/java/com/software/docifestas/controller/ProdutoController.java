package com.software.docifestas.controller;

import com.software.docifestas.dto.produto.ProdutoRequestDTO;
import com.software.docifestas.service.ProdutoService;
import com.software.docifestas.dto.produto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // Codes For Aplications - POST / NÃO MEXER
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
    })

    @Operation(summary = "Cria os produtos no sistema.")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO request) {
        ProdutoResponseDTO produto = produtoService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

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
    public ResponseEntity <ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO request) {
        ProdutoResponseDTO produto = produtoService.atualizarProduto(id, request);
        return ResponseEntity.ok(produto);
    }

    // Codes For Aplications - DELETE / NÃO MEXER
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto removido com sucesso."),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
})
    @Operation(summary = "Remove produtos do sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }
}