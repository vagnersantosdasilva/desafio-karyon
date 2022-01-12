package br.com.bagarote.config;

import br.com.bagarote.model.converter.ProdutoConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final ProdutoConverter produtoConverter;


    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(produtoConverter.getCreateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getUpdateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getProdutoToResponseProduto());
        return modelMapper;
    }
}
