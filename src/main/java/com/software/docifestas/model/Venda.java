package com.software.docifestas.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Venda {
    // Atributos Spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos By Dege
    @ManyToOne
    Usuario usuario;

    @ManyToOne
    Produto produto;

    private int quantidade;
    private BigDecimal valorTotal;
    private LocalDateTime data;

    // Construtor
    public Venda() {
    }

    // G&S
    public Usuario getUsuario() {return usuario;}
    public Produto getProduto() {return produto;}
    public int getQuantidade() {return quantidade;}
    public BigDecimal getValorTotal() {return valorTotal;}
    public LocalDateTime getData() {return data;}

    // --------------------------------------

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public void setProduto(Produto produto) {this.produto = produto;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public void setValorTotal(BigDecimal valorTotal) {this.valorTotal = valorTotal;}
    public void setData(LocalDateTime data) {this.data = data;}

    // MTS
    @Override
    public String toString() {
        return produto + " | Quantidade: " + quantidade + " | Valor: " + valorTotal + " | " + data;
    }
}
