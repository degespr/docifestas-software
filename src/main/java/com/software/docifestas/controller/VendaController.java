package com.software.docifestas.controller;

import com.software.docifestas.dto.VendaRequest;
import com.software.docifestas.model.Venda;
import com.software.docifestas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public Venda criarVenda(@RequestBody VendaRequest request) {return vendaService.registrarVenda(request.getProdutoId(), request.getQuantidade());}

}
