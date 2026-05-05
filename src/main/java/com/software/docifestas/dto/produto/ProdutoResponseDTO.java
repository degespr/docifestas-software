package com.software.docifestas.dto.produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
    // Atributos By Dege
    private Long id;
    private String nomeProduto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // Getter
    public Long getId() {return id;}
    public String getNomeProduto() {return nomeProduto;}
    public String getCategoria() {return categoria;}
    public BigDecimal getPreco() {return preco;}
    public int getEstoque() {return estoque;}

    // -----------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}
    public void setEstoque(int estoque) {this.estoque = estoque;}

}
