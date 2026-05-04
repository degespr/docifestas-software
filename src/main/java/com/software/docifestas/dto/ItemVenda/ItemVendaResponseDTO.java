package com.software.docifestas.dto.ItemVenda;

import java.math.BigDecimal;

public class ItemVendaResponseDTO {
    // Atributos By Dege
    private String nomeProduto;
    private int quantidade;
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
