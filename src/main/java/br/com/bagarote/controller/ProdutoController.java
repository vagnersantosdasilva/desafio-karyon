package br.com.bagarote.controller;
import javax.validation.Valid;

import br.com.bagarote.exception.MessageError;
import br.com.bagarote.model.dto.request.CreateProduto;
import br.com.bagarote.model.dto.request.UpdateProduto;
import br.com.bagarote.model.dto.response.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import br.com.bagarote.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
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

	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	@GetMapping("empresa/{idEmpresa}/produto")
	public ResponseEntity<?> getProdutosByIdProduto(@PathVariable Long idEmpresa) {

		return ResponseEntity.ok().body(produtoService.findProdutosByEmpresa(idEmpresa));
	}


	@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('VENDEDOR')")
	@GetMapping("produto/{idProduto}")
	public ResponseEntity<?> getByIdProduto(@PathVariable Long idProduto) {
		return ResponseEntity.ok().body(produtoService.findById(idProduto));
    }

	@PostMapping("produto")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> create(
			@RequestBody  @Valid CreateProduto createProduto,
			BindingResult result
	) throws Exception {
		MessageError.messageError(result);
		Produto produto = modelMapper.map(createProduto, Produto.class);
		ProdutoResponse response = modelMapper.map(produtoService.create(produto), ProdutoResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	@PutMapping("produto/{idProduto}")
	public ResponseEntity<?> update(
			@PathVariable Long idProduto,
			@RequestBody  @Valid UpdateProduto updateProduto,
			BindingResult result
	) throws Exception {
		MessageError.messageError(result);
		ProdutoResponse response = modelMapper.map(produtoService.update(idProduto,updateProduto), ProdutoResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
