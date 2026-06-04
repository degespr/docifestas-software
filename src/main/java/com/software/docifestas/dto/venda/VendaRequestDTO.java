package com.software.docifestas.dto.venda;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class VendaRequestDTO {

    // Atributos By Dege
    @Schema(description = "Carrinho de compras do usuário", example = "Brigadeiro, Cookie, Bolo de Cenoura")
    private List<ItemVendaRequestDTO> itens;

    // Getter
    public List<ItemVendaRequestDTO> getItens() {return itens;}

    // -------------------------

    // Setter
    public void setItens(List<ItemVendaRequestDTO> itens) {this.itens = itens;}
}