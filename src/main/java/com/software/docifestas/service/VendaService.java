package com.software.docifestas.service;

import com.software.docifestas.model.Produto;
import com.software.docifestas.model.Venda;
import com.software.docifestas.repository.ProdutoRepository;
import com.software.docifestas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda registrarVenda(Long produtoId, int quantidade) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(quantidade);
        venda.setValorTotal(produto.getPreco().multiply(java.math.BigDecimal.valueOf(quantidade)));
        venda.setData(java.time.LocalDateTime.now());

        return vendaRepository.save(venda);
    }

    // Code Rule - Extras
    public List<Venda> listarVendas() {return vendaRepository.findAll();}

    public Venda buscarVenda(Long id) {
        Optional<Venda> buscarVendas = vendaRepository.findById(id);
            if (buscarVendas.isPresent()) {
                return buscarVendas.get();

            } throw new IllegalArgumentException("Venda não encontrada!");
    }
}
