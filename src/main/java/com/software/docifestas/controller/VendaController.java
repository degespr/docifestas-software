package com.software.docifestas.controller;

import com.software.docifestas.dto.VendaRequest;
import com.software.docifestas.model.Venda;
import com.software.docifestas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public Venda criarVenda(@RequestBody VendaRequest request) {return vendaService.registrarVenda(request.getUsuarioId(), request.getProdutoId(), request.getQuantidade());}

    @GetMapping
    public List<Venda> listarVendas() {return vendaService.listarVendas();}

    @GetMapping("/{id}")
    public Venda buscarVenda(@PathVariable Long id) {return vendaService.buscarVenda(id);}

}
