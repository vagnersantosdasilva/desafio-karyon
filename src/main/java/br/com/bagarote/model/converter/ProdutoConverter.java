package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.request.CreateProdutoRequest;
import br.com.bagarote.model.dto.request.UpdateProdutoRequest;
import br.com.bagarote.model.dto.response.ProdutoResponse;
import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.model.entity.Produto;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Objects;

@Getter
@Component
public class ProdutoConverter {
    Converter<CreateProdutoRequest,Produto> createProdutoToProduto = new Converter<CreateProdutoRequest, Produto>(){
        @Override
        public Produto convert(MappingContext<CreateProdutoRequest, Produto> mappingContext) {
            return Produto.builder()
                    .produto(mappingContext.getSource().getProduto())
                    .imagemProduto(mappingContext.getSource().getImagemProdutoByte())
                    .descricao(mappingContext.getSource().getDescricao())
                    .valorBase(mappingContext.getSource().getValor())
                    .empresa( Objects.nonNull(mappingContext.getSource().getIdEmpresa()) ? Empresa.builder().idEmpresa(mappingContext.getSource().getIdEmpresa()).build() : null )
                    .build();
        }
    };

    Converter<Produto, ProdutoResponse> produtoToResponseProduto = new Converter< Produto,ProdutoResponse>(){
        @Override
        public ProdutoResponse convert(MappingContext<Produto,ProdutoResponse> mappingContext) {
            return ProdutoResponse.builder()
                    .idProduto(mappingContext.getSource().getIdProduto())
                    .produto(mappingContext.getSource().getProduto())
                    .imagemProduto(mappingContext.getSource().getImagemProdutoBase64())
                    .descricao(mappingContext.getSource().getDescricao())
                    .valor(mappingContext.getSource().getValorBase())
                    .idEmpresa(mappingContext.getSource().getEmpresa().getIdEmpresa())
                    .build();
        }
    };

    Converter<UpdateProdutoRequest,Produto> updateProdutoToProduto = new Converter<UpdateProdutoRequest, Produto>(){
        @Override
        public Produto convert(MappingContext<UpdateProdutoRequest, Produto> mappingContext) {
            return Produto.builder()
                    .idProduto(mappingContext.getSource().getIdProduto())
                    .produto(mappingContext.getSource().getProduto())
                    .imagemProduto(mappingContext.getSource().getImagemProdutoByte())
                    .descricao(mappingContext.getSource().getDescricao())
                    .valorBase(mappingContext.getSource().getValor())
                    .empresa( Objects.nonNull(mappingContext.getSource().getIdEmpresa()) ? Empresa.builder().idEmpresa(mappingContext.getSource().getIdEmpresa()).build() : null )
                    .build();
        }
    };
}
