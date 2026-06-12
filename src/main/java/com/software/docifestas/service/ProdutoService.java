package com.software.docifestas.service;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;
import com.software.docifestas.dto.produto.ProdutoRequestDTO;
import com.software.docifestas.dto.produto.ProdutoResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.model.Produto;
import com.software.docifestas.repository.ItemVendaRepository;
import com.software.docifestas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    // Connection
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    // Functions of System
    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        Optional<Produto> produtoNoBanco = produtoRepository.findByNomeProduto(request.getNomeProduto());

        // Validation's
        validarNomeProduto(request.getNomeProduto());
        validarEstoque(request.getEstoque());
        validarPreco(request.getPreco());
        validarCategoria(request.getCategoria());

        // Code Run
        if (produtoNoBanco.isPresent()) {
            throw new BusinessException("Já existe um produto com este nome!");
        }

        // Code Rules - Extras
        Produto produto = new Produto();
        produto.setNomeProduto(request.getNomeProduto());
        produto.setCategoria(request.getCategoria());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());

        Produto salvo = produtoRepository.save(produto);

        return toResponseDTO(salvo);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        // Code Run
        return produtos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não existe"));

        // Code Run
        return toResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO request) {
        Produto produtoNoBanco = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
        Optional<Produto> produtoComMesmoNome = produtoRepository.findByNomeProduto(request.getNomeProduto());

        // Validation's
        validarNomeProduto(request.getNomeProduto());
        validarEstoque(request.getEstoque());
        validarPreco(request.getPreco());
        validarCategoria(request.getCategoria());

        // Code Run
        if (produtoComMesmoNome.isPresent() && !produtoComMesmoNome.get().getId().equals(id)) {
            throw new BusinessException("Já existe um produto com esse nome.");
        }

        // Code Rules - Extras
        produtoNoBanco.setNomeProduto(request.getNomeProduto());
        produtoNoBanco.setCategoria(request.getCategoria());
        produtoNoBanco.setPreco(request.getPreco());
        produtoNoBanco.setEstoque(request.getEstoque());

        Produto atualizado = produtoRepository.save(produtoNoBanco);

        return toResponseDTO(atualizado);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

        // Code Run
        boolean produtoJaVendido = itemVendaRepository.existsByProdutoId(produto.getId());
        if (produtoJaVendido) {
            throw new BusinessException("Não é possível excluir um produto que possui vendas registradas.");
        }

        produtoRepository.delete(produto);
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {

        // Code Run
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produto.getId());
        response.setNomeProduto(produto.getNomeProduto());
        response.setCategoria(produto.getCategoria());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());

        return response;
    }


    // Method's for Refactoring - NomeProduto
    private void validarNomeProduto(String nomeProduto) {
        if (nomeProduto == null) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");
        }

        if (nomeProduto.trim().isEmpty()) {
            throw new BusinessException("Nome inválido! Insira um nome válido.");
        }
    }

    // Method's for Refactoring - Estoque
    private void validarEstoque(Integer estoque) {
        if (estoque == null || estoque < 0) {
            throw new BusinessException("Estoque não pode ser negativo.");
        }
    }

    // Method's for Refactoring - Preço
    private void validarPreco(BigDecimal preco) {
        if (preco == null) {
            throw new BusinessException("Preço inválido! Insira um preço válido.");
        }

        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Preço inválido! O preço deve ser maior que zero.");
        }
    }

    // Method's for Refactoring - Categoria
    private void validarCategoria(String categoria) {
        if (categoria == null) {
            throw new BusinessException("Categoria não pode estar vazia.");
        }

        if (categoria.trim().isEmpty()) {
            throw new BusinessException("Categoria não pode estar vazia.");
        }
    }
}