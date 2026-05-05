package com.software.docifestas.dto.produto;

import java.math.BigDecimal;

public class ProdutoRequestDTO {
    // Atributos By Dege
    private String nomeProduto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // Getter
    public String getNomeProduto() {return nomeProduto;}
    public String getCategoria() {return categoria;}
    public BigDecimal getPreco() {return preco;}
    public int getEstoque() {return estoque;}

    // -----------------------------

    // Setter
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setEstoque(int estoque) {this.estoque = estoque;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}
}
