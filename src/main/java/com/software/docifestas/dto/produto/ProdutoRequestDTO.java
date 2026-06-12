package com.software.docifestas.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProdutoRequestDTO {

    // Atributos By Dege
    @Schema(description = "Nome do produto", example = "Brigadeiro")
    private String nomeProduto;

    @Schema(description = "Categoria do produto", example = "Doce")
    private String categoria;

    @Schema(description = "Preço do produto", example = "3.50")
    private BigDecimal preco;

    @Schema(description = "Quantidade disponível em estoque", example = "100")
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
