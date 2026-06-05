package com.software.docifestas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.software.docifestas.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{

    // Connection
    boolean existsByProdutoId(Long produtoId);

}
