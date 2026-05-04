package com.software.docifestas.dto.ItemVenda;

public class ItemVendaRequestDTO {
    // Atributos By Dege
    private Long produtoId;
    private int quantidade;

    // Getter
    public Long getProdutoId() {return produtoId;}
    public int getQuantidade() {return quantidade;}

    // -----------------------------

    // Setter
    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}