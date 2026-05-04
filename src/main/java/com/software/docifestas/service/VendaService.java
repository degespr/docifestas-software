package com.software.docifestas.service;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;
import com.software.docifestas.dto.venda.VendaRequestDTO;
import com.software.docifestas.dto.venda.VendaResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.model.ItemVenda;
import com.software.docifestas.model.Produto;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.model.Venda;
import com.software.docifestas.repository.ProdutoRepository;
import com.software.docifestas.repository.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    public VendaResponseDTO registrarVenda(VendaRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setData(java.time.LocalDateTime.now());
        java.math.BigDecimal valorTotal = java.math.BigDecimal.ZERO;

        // Code Run
        for (ItemVendaRequestDTO item : request.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

            if (item.getQuantidade() <= 0) {
                throw new BusinessException("Quantidade inválida!");
            }

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente!");
            }

            java.math.BigDecimal precoUnitario = produto.getPreco();
            java.math.BigDecimal subtotal = precoUnitario.multiply(
                    java.math.BigDecimal.valueOf(item.getQuantidade())
            );

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setPrecoUnitario(precoUnitario);
            itemVenda.setSubtotal(subtotal);

            venda.getItens().add(itemVenda);

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
            valorTotal = valorTotal.add(subtotal);
        }

        venda.setValorTotal(valorTotal);
        Venda vendaSalva = vendaRepository.save(venda);
        return toDTO(vendaSalva);
    }

    // Code Rule - Extras
    public List<Venda> listarVendas() {return vendaRepository.findAll();}

    public Venda buscarVenda(Long id) {
        Optional<Venda> buscarVendas = vendaRepository.findById(id);
            if (buscarVendas.isPresent()) {
                return buscarVendas.get();

            } throw new ResourceNotFoundException("Venda não encontrada!");
    }

    public List<Venda> listarVendasDoUsuario(Long usuarioId) {
        return vendaRepository.findByUsuarioId(usuarioId);
    }

    public List<Venda> vendaPorProduto(Long produtoId) {
        return vendaRepository.findByProdutoId(produtoId);
    }

    public VendaResponseDTO toDTO(Venda venda) {
        VendaResponseDTO dto = new VendaResponseDTO();

        dto.setId(venda.getId());
        dto.setNomeUsuario(venda.getUsuario().getNome());
        dto.setValorTotal(venda.getValorTotal());
        dto.setData(venda.getData());
        dto.setItens(venda.getItens());

        return dto;
    }
}
