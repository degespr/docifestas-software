package com.software.docifestas.service;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;
import com.software.docifestas.dto.ItemVenda.ItemVendaResponseDTO;
import com.software.docifestas.dto.venda.VendaRequestDTO;
import com.software.docifestas.dto.venda.VendaResponseDTO;
import com.software.docifestas.exception.BusinessException;
import com.software.docifestas.exception.ResourceNotFoundException;
import com.software.docifestas.model.ItemVenda;
import com.software.docifestas.model.Produto;
import com.software.docifestas.model.Usuario;
import com.software.docifestas.model.Venda;
import com.software.docifestas.repository.ProdutoRepository;
import com.software.docifestas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    // Atributos By Dege
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Functions of System
    public VendaResponseDTO registrarVenda(VendaRequestDTO request, Usuario usuario) {

        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setData(java.time.LocalDateTime.now());
        java.math.BigDecimal valorTotal = java.math.BigDecimal.ZERO;

        // Validation's
        validarItensVenda(request.getItens());

        // Code Run
        for (ItemVendaRequestDTO item : request.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

            // Validation's
            validarEstoqueDisponivel(produto, item.getQuantidade());

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
    public List<VendaResponseDTO> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaResponseDTO> listaDTO = new ArrayList<>();

        for (Venda venda : vendas) {
            listaDTO.add(toDTO(venda));
        } return listaDTO;
    }

    public List<VendaResponseDTO> listarMinhasVendas(Usuario usuario) {
        List<Venda> minhasVendas = vendaRepository.findByUsuarioId(usuario.getId());
        List<VendaResponseDTO> listaDTO = new ArrayList<>();

        for (Venda v : minhasVendas) {
            listaDTO.add(toDTO(v));
        } return listaDTO;
    }

    // Method for Conversion - Não Modificar
    public VendaResponseDTO buscarVenda(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada!"));

        return toDTO(venda);
    }

    // Method To DTO - Não Modificar
    private VendaResponseDTO toDTO(Venda venda) {
        VendaResponseDTO dto = new VendaResponseDTO();

        dto.setId(venda.getId());
        dto.setNomeUsuario(venda.getUsuario().getNome());
        dto.setValorTotal(venda.getValorTotal());
        dto.setData(venda.getData());

        List<ItemVendaResponseDTO> itensDTO = new ArrayList<>();
        for (ItemVenda item : venda.getItens()) {
            ItemVendaResponseDTO itemDTO = new ItemVendaResponseDTO();
            itemDTO.setNomeProduto(item.getProduto().getNomeProduto());
            itemDTO.setQuantidade(item.getQuantidade());
            itemDTO.setSubtotal(item.getSubtotal());

            itensDTO.add(itemDTO);

        }

        dto.setItens(itensDTO);
        return dto;
    }

    // Method's for Refactoring - Quantidade
    private void validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new BusinessException("A quantidade de produtos deve ser maior que zero.");
        }
    }
    // Method's for Refactoring - Item Venda
    private void validarItensVenda(List<ItemVendaRequestDTO> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new BusinessException("A venda deve conter ao menos um produto.");
        }

        for (ItemVendaRequestDTO item : itens) {
            validarQuantidade(item.getQuantidade());
        }
    }

    // Method's for Refactoring - Estoque
    private void validarEstoqueDisponivel(Produto produto, Integer quantidade) {
        if (produto.getEstoque() < quantidade) {
            throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }
    }
}
