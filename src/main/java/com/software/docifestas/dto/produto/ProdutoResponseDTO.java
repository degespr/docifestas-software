package com.software.docifestas.dto.produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
    // Atributos By Dege
    private Long id;
    private String produto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // G&S
    public Long getId() {return id;}
    public String getProduto() {return produto;}
    public String getCategoria() {return categoria;}
    public BigDecimal getPreco() {return preco;}
    public int getEstoque() {return estoque;}

    // -----------------------------

    public void setId(Long id) {this.id = id;}
    public void setProduto(String produto) {this.produto = produto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}
    public void setEstoque(int estoque) {this.estoque = estoque;}

}
