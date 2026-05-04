package com.software.docifestas.dto.venda;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;

import java.util.List;

public class VendaRequestDTO {

    // Atributos By Dege
    private Long usuarioId;
    private List<ItemVendaRequestDTO> itens;

    // G&S
    public Long getUsuarioId() {return usuarioId;}
    public List<ItemVendaRequestDTO> getItens() {return itens;}

    public void setUsuarioId(Long usuarioId) {this.usuarioId = usuarioId;}
    public void setItens(List<ItemVendaRequestDTO> itens) {this.itens = itens;}
}