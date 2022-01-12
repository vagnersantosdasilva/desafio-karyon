package br.com.bagarote.controller;

import br.com.bagarote.exception.ResourceNotFoundException;
import br.com.bagarote.model.dto.request.CreateCliente;
import br.com.bagarote.model.dto.request.UpdateCliente;
import br.com.bagarote.model.dto.response.ClienteResponse;
import br.com.bagarote.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bagarote.model.entity.Cliente;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@GetMapping("cliente")
	public ResponseEntity<?> getAll() {
	    return ResponseEntity.ok().body(clienteService.findAll());
    }

	@GetMapping("cliente/{idCliente}")
	public ResponseEntity<?> getByIdCliente(@PathVariable Long idCliente) throws ResourceNotFoundException {
	    return ResponseEntity.ok().body(clienteService.findById(idCliente));
    }

	@PostMapping("cliente")
	public ResponseEntity<?> create(@RequestBody @Valid CreateCliente createCliente,BindingResult result) throws Exception {
	    if (result.hasErrors()) throw new Exception(result.getAllErrors().get(0).getDefaultMessage());

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(createCliente));
    }
	
	@PutMapping("cliente/{idCliente}")
	public ResponseEntity<?> update(@PathVariable Long idCliente,@RequestBody  @Valid UpdateCliente updateCliente, BindingResult result) throws Exception {
		if (result.hasErrors()) throw new Exception(result.getAllErrors().get(0).getDefaultMessage());
		ClienteResponse response = clienteService.update(idCliente,updateCliente);
		return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
}
