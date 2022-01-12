package br.com.bagarote.controller;
import javax.validation.Valid;
import br.com.bagarote.model.dto.request.CreateProdutoRequest;
import br.com.bagarote.model.dto.request.UpdateProdutoRequest;
import br.com.bagarote.model.dto.response.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import br.com.bagarote.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.bagarote.model.entity.Produto;

import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;
	private final ModelMapper modelMapper;

	@Autowired
	public ProdutoController(@Lazy ModelMapper modelMapper, @Lazy ProdutoService produtoService){
		this.produtoService =produtoService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("produto")
	public ResponseEntity<?> getAll() {

		return ResponseEntity
				.ok()
				.body(
						produtoService
								.findAll()
								.stream()
								.map(produto-> modelMapper.map(produto, ProdutoResponse.class))
								.collect(Collectors.toList())
				);

    }

	@GetMapping("produto/{idProduto}")
	public ResponseEntity<?> getByIdProduto(@PathVariable Long idProduto) {
		return ResponseEntity.ok().body(produtoService.findById(idProduto));
    }

	@PostMapping("produto")
	public ResponseEntity<?> create(@RequestBody  @Valid CreateProdutoRequest createProduto) {
		Produto produto = modelMapper.map(createProduto, Produto.class);
		ProdutoResponse response = modelMapper.map(produtoService.create(produto), ProdutoResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

	@PutMapping("produto/{idProduto}")
	public ResponseEntity<?> update(@PathVariable Long idProduto,@RequestBody  @Valid  UpdateProdutoRequest updateProduto) {
		//Produto produtoUpdate = modelMapper.map(updateProduto, Produto.class);
		ProdutoResponse response = modelMapper.map(produtoService.update(idProduto,updateProduto), ProdutoResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
