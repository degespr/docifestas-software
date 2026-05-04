package com.software.docifestas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {
    // Atributos Spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos By Dege
    @ManyToOne
    private Usuario usuario;
    private BigDecimal valorTotal;
    private LocalDateTime data;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    // Construtor
    public Venda() {
    }

    // Getter
    public Long getId() {return id;}
    public Usuario getUsuario() {return usuario;}
    public List<ItemVenda> getItens() {return itens;}
    public BigDecimal getValorTotal() {return valorTotal;}
    public LocalDateTime getData() {return data;}

    // --------------------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public void setItens(List<ItemVenda> itens) {this.itens = itens;}
    public void setValorTotal(BigDecimal valorTotal) {this.valorTotal = valorTotal;}
    public void setData(LocalDateTime data) {this.data = data;}

    // MTS
    @Override
    public String toString() {
        return " | Valor: " + valorTotal + " | " + data;
    }
}
