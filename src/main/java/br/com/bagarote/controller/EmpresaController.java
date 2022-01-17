package br.com.bagarote.controller;

import br.com.bagarote.service.EmpresaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.model.entity.Empresa;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmpresaController {
	
	private final EmpresaService empresaService;
	
	@GetMapping("empresa")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> getaAll() {
	    return ResponseEntity.ok().body(empresaService.findAll());
    }

	@GetMapping("empresa/{idEmpresa}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> getByIdEmpresa(@PathVariable Long idEmpresa) {
	    return ResponseEntity.ok().body(empresaService.findById(idEmpresa));
    }

	@PostMapping("empresa")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> create(@RequestBody Empresa createEmpresa) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(createEmpresa));
    }

	@PutMapping("empresa/{idEmpresa}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> update(@PathVariable Long idEmpresa, @RequestBody Empresa updateEmpresa) {
		Empresa empresa = empresaService.findById(idEmpresa);
		BeanUtils.copyProperties(updateEmpresa, empresa);
	    return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresa));
    }
}
