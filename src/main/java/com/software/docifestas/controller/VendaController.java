package com.software.docifestas.controller;

import com.software.docifestas.dto.venda.VendaRequestDTO;
import com.software.docifestas.dto.venda.VendaResponseDTO;
import com.software.docifestas.model.Venda;
import com.software.docifestas.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Cria uma venda no sistema.")
    @PostMapping
    public VendaResponseDTO criarVenda(@RequestBody VendaRequestDTO request) {return vendaService.registrarVenda(request);}

    @Operation(summary = "Lista todas as vendas.")
    @GetMapping
    public List<VendaResponseDTO> listarVendas() {return vendaService.listarVendas();}

    @Operation(summary = "Busca uma venda no sistema.")
    @GetMapping("/{id}")
    public VendaResponseDTO buscarVenda(@PathVariable Long id) {return vendaService.buscarVenda(id);}
}
