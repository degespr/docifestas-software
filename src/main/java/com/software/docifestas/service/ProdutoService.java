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
        Optional<Produto> produtoNoBanco = produtoRepository.findByProduto(produto.getProduto());

        if (produtoNoBanco.isPresent()) {
            throw new RuntimeException("Já existe um produto com este nome!");
        }

        return produtoRepository.save(produto);
    }

    // Code Rule - Extras
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // MTS
    public Produto buscarPorId(Long id) {

        Optional<Produto> buscarProduto = produtoRepository.findById(id);
            if (buscarProduto.isPresent()) {
                return buscarProduto.get();

            } throw new IllegalArgumentException("Produto não encontrado!");
        }

    public Produto atualizarProduto(Long id, Produto produto) {
        Produto produtoNoBanco = buscarPorId(id);

        produtoNoBanco.setProduto(produto.getProduto());
        produtoNoBanco.setCategoria(produto.getCategoria());
        produtoNoBanco.setPreco(produto.getPreco());
        produtoNoBanco.setEstoque(produto.getEstoque());

        return produtoRepository.save(produtoNoBanco);
    }

    public void deletarProduto(Long id) {
        buscarPorId(id);
        produtoRepository.deleteById(id);
    }
}
