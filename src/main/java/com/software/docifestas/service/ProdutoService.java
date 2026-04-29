package com.software.docifestas.service;

import com.software.docifestas.dto.produto.ProdutoRequestDTO;
import com.software.docifestas.dto.produto.ProdutoResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.model.Produto;
import com.software.docifestas.repository.ProdutoRepository;
import com.software.docifestas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        Optional<Produto> produtoNoBanco = produtoRepository.findByProduto(request.getProduto());

        if (produtoNoBanco.isPresent()) {
            throw new BusinessException("Já existe um produto com este nome!");
        }

        if (request.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Preço deve ser maior que zero");
        }

        Produto produto = new Produto();
        produto.setProduto(request.getProduto());
        produto.setCategoria(request.getCategoria());
        produto.setPreco(request.getPreco());
        produto.setEstoque(request.getEstoque());

        Produto salvo = produtoRepository.save(produto);

        return toResponseDTO(salvo);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não existe"));

        return toResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO request) {
        Produto produtoNoBanco = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

        if (request.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Preço deve ser maior que zero");
        }

        produtoNoBanco.setProduto(request.getProduto());
        produtoNoBanco.setCategoria(request.getCategoria());
        produtoNoBanco.setPreco(request.getPreco());
        produtoNoBanco.setEstoque(request.getEstoque());

        Produto atualizado = produtoRepository.save(produtoNoBanco);

        return toResponseDTO(atualizado);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

        produtoRepository.delete(produto);
    }

    public List<Object[]> produtosMaisVendidos() {
        return vendaRepository.produtosMaisVendidos();
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produto.getId());
        response.setProduto(produto.getProduto());
        response.setCategoria(produto.getCategoria());
        response.setPreco(produto.getPreco());
        response.setEstoque(produto.getEstoque());

        return response;
    }
}