package com.software.docifestas.dto;

public class VendaRequest {

    // Atributos By Dege
    private Long produtoId;
    private int quantidade;

    // G&S
    public Long getProdutoId() {return produtoId;}
    public int getQuantidade() {return quantidade;}

    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}