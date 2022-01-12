package br.com.bagarote.service;

import br.com.bagarote.model.dto.request.CreateProdutoRequest;
import br.com.bagarote.model.dto.request.UpdateProdutoRequest;
import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.model.entity.Produto;
import br.com.bagarote.repository.EmpresaRepository;
import br.com.bagarote.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, EmpresaRepository empresaRepository, ModelMapper modelMapper){
        this.produtoRepository = produtoRepository;
        this.empresaRepository = empresaRepository;
        this.modelMapper = modelMapper;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long idProduto) {
        return produtoRepository.findById(idProduto).orElse(null);
    }


    public Produto create(Produto produto){

        return produtoRepository.save(produto);
    }

    //TODO:Verificar se id de url é igual a id do body
    public Produto update(Long idProduto, UpdateProdutoRequest updateProduto){
        Produto produtoExistente = produtoRepository.findById(idProduto).orElse(null);
        if (produtoExistente ==null) throw new RuntimeException("Id de produto não pode ser nulo");
        Produto produto = modelMapper.map(updateProduto, Produto.class);
        return produtoRepository.save(produto);
    }

    public Object save(Produto produto) {

        return produtoRepository.save(produto);
    }
}
