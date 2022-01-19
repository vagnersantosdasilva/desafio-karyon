package br.com.bagarote.controller;

import br.com.bagarote.exception.BusinessRuleException;
import br.com.bagarote.exception.MessageError;
import br.com.bagarote.model.dto.request.CreateEmpresa;
import br.com.bagarote.model.dto.request.UpdateEmpresa;
import br.com.bagarote.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import javax.validation.Valid;



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
	public ResponseEntity<?> create(
			@Valid @RequestBody CreateEmpresa createEmpresa,
			BindingResult result) throws Exception {

		MessageError.messageError(result);
	    return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.create(createEmpresa));
    }

	@PutMapping("empresa/{idEmpresa}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> update(
			@Valid @PathVariable Long idEmpresa,
			@RequestBody UpdateEmpresa updateEmpresa,
			BindingResult result) throws Exception {
		MessageError.messageError(result);
	    return ResponseEntity.status(HttpStatus.OK).body(empresaService.update(idEmpresa,updateEmpresa));
    }


}
