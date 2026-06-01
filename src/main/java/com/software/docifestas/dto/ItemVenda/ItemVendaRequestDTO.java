package com.software.docifestas.dto.ItemVenda;

import io.swagger.v3.oas.annotations.media.Schema;

public class ItemVendaRequestDTO {

    // Atributos By Dege
    @Schema(description = "ID do produto", example = "1")
    private Long produtoId;

    @Schema(description = "Quantidade do produto em estoque", example = "20")
    private int quantidade;

    // Getter
    public Long getProdutoId() {return produtoId;}
    public int getQuantidade() {return quantidade;}

    // -----------------------------

    // Setter
    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}