package br.com.bagarote.config;

import br.com.bagarote.model.converter.ClienteConverter;
import br.com.bagarote.model.converter.ProdutoConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final ProdutoConverter produtoConverter;
    private final ClienteConverter clienteConverter;

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(produtoConverter.getCreateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getUpdateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getProdutoToResponseProduto());

        modelMapper.addConverter(clienteConverter.getCreateClienteToCliente());
        modelMapper.addConverter(clienteConverter.getUpdateClienteToCliente());
        modelMapper.addConverter(clienteConverter.getClienteToResponseCliente());

        return modelMapper;
    }
}
