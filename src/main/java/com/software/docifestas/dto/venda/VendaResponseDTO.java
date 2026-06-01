package com.software.docifestas.dto.venda;

import com.software.docifestas.dto.ItemVenda.ItemVendaResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VendaResponseDTO {

    // Atributos By Dege
    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário", example = "Diego Oliveira")
    private String nomeUsuario;

    @Schema(description = "Preço da compra", example = "R$ 10.00")
    private BigDecimal valorTotal;

    @Schema(description = "Data da compra", example = "2026/01/01")
    private LocalDateTime data;

    @Schema(description = "Carrinho de compras do usuário", example = "Brigadeiro, Cookie, Bolo de Cenoura")
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
