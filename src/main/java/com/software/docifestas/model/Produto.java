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
    private String nomeProduto;
    private String categoria;
    private BigDecimal preco;
    private int estoque;

    // Construtor
    public Produto() {
    }

    // Getter
    public Long getId() {return id;}
    public String getNomeProduto() {return nomeProduto;}
    public String getCategoria() {return categoria;}
    public int getEstoque() {return estoque;}
    public BigDecimal getPreco() {return preco;}

    // --------------------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setEstoque(int estoque) {

        // Code Run
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo!");

        } this.estoque = estoque;
    }

    public void setPreco(BigDecimal preco) {

        // Code Run
        // Se o resultado da comparação for -1, significa que é menor que zero
        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    // Method To String
    @Override
    public String toString() {
        return nomeProduto + " |  Categoria: " + categoria + " | Estoque: " + estoque + " |  Custa R$ " +preco;

    }
}