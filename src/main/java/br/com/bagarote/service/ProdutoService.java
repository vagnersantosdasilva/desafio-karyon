package br.com.bagarote.service;

import br.com.bagarote.model.Produto;
import br.com.bagarote.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long idProduto) {
        return produtoRepository.findById(idProduto).orElse(null);
    }

    public Object save(Produto createProduto) {
        return produtoRepository.save(createProduto);
    }
}
