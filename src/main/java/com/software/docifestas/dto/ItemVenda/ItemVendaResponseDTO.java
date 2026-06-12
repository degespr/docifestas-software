package com.software.docifestas.dto.ItemVenda;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ItemVendaResponseDTO {

    // Atributos By Dege
    @Schema(description = "Nome do produto", example = "Brigadeiro")
    private String nomeProduto;

    @Schema(description = "Quantidade que o cliente deseja comprar", example = "20")
    private int quantidade;

    @Schema(description = "Preço do produto", example = "3.50")
    private BigDecimal subtotal;

    // Getter
    public String getNomeProduto() {return nomeProduto;}
    public int getQuantidade() {return quantidade;}
    public BigDecimal getSubtotal() {return subtotal;}

    // -----------------------------

    // Setter
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public void setSubtotal(BigDecimal subtotal) {this.subtotal = subtotal;}
}
