package br.com.bagarote.repository;

import br.com.bagarote.model.entity.Produto;
import br.com.bagarote.model.entity.VendaProduto;
import br.com.bagarote.model.entity.VendaProduto.VendaProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaProdutoRepository extends JpaRepository<VendaProduto, VendaProdutoId>{

    @Query("Select v from VendaProduto v where v.vendaProdutoId.venda.idVenda =?1")
    List<VendaProduto> findByIdVenda(Long idVenda);
}
