package com.software.docifestas.dto.venda;

import com.software.docifestas.dto.ItemVenda.ItemVendaRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class VendaRequestDTO {

    // Atributos By Dege
    @Schema(description = "ID do usuário", example = "1")
    private Long usuarioId;

    @Schema(description = "Carrinho de compras do usuário", example = "Brigadeiro, Cookie, Bolo de Cenoura")
    private List<ItemVendaRequestDTO> itens;

    // Getter
    public Long getUsuarioId() {return usuarioId;}
    public List<ItemVendaRequestDTO> getItens() {return itens;}

    // -------------------------

    // Setter
    public void setUsuarioId(Long usuarioId) {this.usuarioId = usuarioId;}
    public void setItens(List<ItemVendaRequestDTO> itens) {this.itens = itens;}
}