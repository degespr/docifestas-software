package com.software.docifestas.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    // Atributos By Dege
    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Brigadeiro")
    private String nomeProduto;

    @Schema(description = "Categoria do produto", example = "Doce")
    private String categoria;

    @Schema(description = "Preço do produto", example = "3.50")
    private BigDecimal preco;

    @Schema(description = "Quantidade em estoque", example = "100")
    private Integer estoque;

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
