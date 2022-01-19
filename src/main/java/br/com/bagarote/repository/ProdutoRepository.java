package br.com.bagarote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bagarote.model.entity.Produto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("Select p from Produto p where p.empresa.idEmpresa = ?1")
    List<Produto> findByEmpresa(Long idEmpresa);
}
