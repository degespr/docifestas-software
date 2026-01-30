package com.software.docifestas.service;

import com.software.docifestas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.docifestas.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        // 1. O repository busca no banco e guarda o resultado na "caixa" (Optional)
        Optional<Produto> produtoNoBanco = produtoRepository.findByProduto(produto.getProduto());

        // 2. Você verifica: "A caixa está cheia? (Ou seja, o produto já existe?)"
        if (produtoNoBanco.isPresent()) {
            throw new RuntimeException("Já existe um produto com este nome!");
        }

        // 3. Se a caixa estiver vazia, o código chega aqui e salva
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
