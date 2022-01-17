package br.com.bagarote.repository;

import br.com.bagarote.model.dto.response.VendaDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bagarote.model.entity.Venda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends PagingAndSortingRepository<Venda, Long> {

    @Query("Select v from Venda v where v.empresa.idEmpresa = ?2 and v.cliente.idCliente=?1")
    Page<Venda> findVendaByClienteEmpresa(Long idCliente, Long idEmpresa,Pageable pageable );

    @Query("Select v from Venda v where v.idVenda = ?1 and v.empresa.idEmpresa = ?2")
    Venda findByIdVendaIdEmpresa(Long idVenda,Long idEmpresa);


}
