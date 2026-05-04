package com.software.docifestas.dto.venda;

import com.software.docifestas.dto.ItemVenda.ItemVendaResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VendaResponseDTO {
    // Atributos By Dege
    private Long id;
    private String nomeUsuario;
    private BigDecimal valorTotal;
    private LocalDateTime data;
    private List<ItemVendaResponseDTO> itens;

    // Getter
    public Long getId() {return id;}
    public String getNomeUsuario() {return nomeUsuario;}
    public BigDecimal getValorTotal() {return valorTotal;}
    public LocalDateTime getData() {return data;}
    public List<ItemVendaResponseDTO> getItens() {return itens;}

    // -----------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}
    public void setValorTotal(BigDecimal valorTotal) {this.valorTotal = valorTotal;}
    public void setData(LocalDateTime data) {this.data = data;}
    public void setItens(List<ItemVendaResponseDTO> itens) {this.itens = itens;}
}
