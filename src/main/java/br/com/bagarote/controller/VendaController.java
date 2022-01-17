package br.com.bagarote.controller;

import br.com.bagarote.model.dto.request.CreateVenda;
import br.com.bagarote.service.VendaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import br.com.bagarote.model.entity.Venda;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class VendaController {
	
	//private final VendaRepository vendaRepository;
	private final VendaService vendaService;
	
	/*@GetMapping("venda")
	public ResponseEntity<?> getAll() {
	    return ResponseEntity.ok().body(vendaService.findAll());
    }
	*/
	@PostMapping("venda")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> create(@RequestBody @Valid CreateVenda createVendaRequest, BindingResult result) throws Exception {

		if (result.hasErrors()) throw new Exception(result.getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.create(createVendaRequest));
    }
	@GetMapping("venda/{idVenda}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> getVendaByIdVenda(@PathVariable Long idVenda) {
	    return ResponseEntity.ok().body(vendaService.findById(idVenda));
    }

	@GetMapping("cliente/{idCliente}/empresa/{idEmpresa}/venda")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> getVendaByIdClienteIdEmpresa(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 5) Pageable page,
			@PathVariable Long idCliente,
			@PathVariable Long idEmpresa
	) {

		return ResponseEntity.ok().body(vendaService.vendaByClienteAndEnpresa(page,idCliente,idEmpresa));
	}

	@GetMapping("empresa/{idEmpresa}/venda/{idVenda}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	public ResponseEntity<?> getVendaByIdVendaIdEmpresa(
			@PathVariable Long idEmpresa ,
			@PathVariable Long idVenda
	) {

		return ResponseEntity.ok().body(vendaService.vendaByIdVendaIdEmpresa(idVenda,idEmpresa));
	}

}
