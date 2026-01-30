package com.software.docifestas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produto {
    // Atributos Spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos By Dege
    private String produto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // Construtor
    public Produto() {
    }

    // G&S

    public String getProduto() {return produto;}
    public String getCategoria() {return categoria;}
    public int getEstoque() {return estoque;}
    public BigDecimal getPreco() {return preco;}

    // --------------------------------------

    public void setProduto(String produto) {this.produto = produto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo!");

        } this.estoque = estoque;
    }
    public void setPreco(BigDecimal preco) {
        // Se o resultado da comparação for -1, significa que é menor que zero
        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    // MTS
    @Override
    public String toString() {
        return produto + " |  Categoria: " + categoria + " | Estoque: " + estoque + " |  Custa R$ " +preco;

    }
}