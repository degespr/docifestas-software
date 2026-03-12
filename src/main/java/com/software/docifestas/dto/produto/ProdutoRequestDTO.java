package com.software.docifestas.dto.produto;

import java.math.BigDecimal;

public class ProdutoRequestDTO {
    // Atributos By Dege
    private String produto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // G&S
    public String getProduto() {return produto;}
    public String getCategoria() {return categoria;}
    public BigDecimal getPreco() {return preco;}
    public int getEstoque() {return estoque;}

    // -----------------------------

    public void setProduto(String produto) {this.produto = produto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setEstoque(int estoque) {this.estoque = estoque;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}
}
