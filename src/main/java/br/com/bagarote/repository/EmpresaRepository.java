package br.com.bagarote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bagarote.model.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
