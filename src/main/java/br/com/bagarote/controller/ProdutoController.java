package br.com.bagarote.controller;

import br.com.bagarote.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.bagarote.model.Produto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;

	@GetMapping("produto")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(produtoService.findAll());
    }

	@GetMapping("produto/{idProduto}")
	public ResponseEntity<?> getByIdProduto(@PathVariable Long idProduto) {
		return ResponseEntity.ok().body(produtoService.findById(idProduto));
    }

	@PostMapping("produto")
	public ResponseEntity<?> create(@RequestBody Produto createProduto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(createProduto));
    }
	@PutMapping("produto/{idProduto}")
	public ResponseEntity<?> update(@PathVariable Long idProduto, @RequestBody Produto updateProduto) {
		Produto produto = produtoService.findById(idProduto);
		if(produto == null)
			ResponseEntity.status(HttpStatus.OK).build();
	    return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));
    }
}
