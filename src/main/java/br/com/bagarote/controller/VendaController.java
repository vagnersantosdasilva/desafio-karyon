package br.com.bagarote.controller;

import br.com.bagarote.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.bagarote.model.Venda;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class VendaController {
	
	//private final VendaRepository vendaRepository;
	private final VendaService vendaService;
	
	@GetMapping("venda")
	public ResponseEntity<?> getAll() {
	    return ResponseEntity.ok().body(vendaService.findAll());
    }

	@PostMapping("venda")
	public ResponseEntity<?> create(@RequestBody Venda createVendaRequest) {
		createVendaRequest.getProdutos().forEach(p->p.getVendaProdutoId().setVenda(createVendaRequest));
	    return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.save(createVendaRequest));
    }
	@GetMapping("venda/{idVenda}")
	public ResponseEntity<?> getVendaByIdVenda(@PathVariable Long idVenda) {
	    return ResponseEntity.ok().body(vendaService.findById(idVenda));
    }
}
