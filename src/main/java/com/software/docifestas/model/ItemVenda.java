package com.software.docifestas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemVenda {
    // Atributos By Spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos By Dege
    @ManyToOne
    private Venda venda;
    @ManyToOne
    private Produto produto;

    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    // Getter
    public Long getId() {return id;}
    public Venda getVenda() {return venda;}
    public Produto getProduto() {return produto;}
    public int getQuantidade() {return quantidade;}
    public BigDecimal getPrecoUnitario() {return precoUnitario;}
    public BigDecimal getSubtotal() {return subtotal;}

    // --------------------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setVenda(Venda venda) {this.venda = venda;}
    public void setProduto(Produto produto) {this.produto = produto;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public void setPrecoUnitario(BigDecimal precoUnitario) {this.precoUnitario = precoUnitario;}
    public void setSubtotal(BigDecimal subtotal) {this.subtotal = subtotal;}
}
