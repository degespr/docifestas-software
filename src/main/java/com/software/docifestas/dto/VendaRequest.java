package com.software.docifestas.dto;

public class VendaRequest {

    // Atributos By Dege
    private Long produtoId;
    private Long usuarioId;
    private int quantidade;

    // G&S
    public Long getProdutoId() {return produtoId;}
    public Long getUsuarioId() {return usuarioId;}
    public int getQuantidade() {return quantidade;}

    public void setProdutoId(Long produtoId) {this.produtoId = produtoId;}
    public void setUsuarioId(Long usuarioId) {this.usuarioId = usuarioId;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}