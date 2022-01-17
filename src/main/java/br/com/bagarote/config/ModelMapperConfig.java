package br.com.bagarote.config;

import br.com.bagarote.model.converter.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final ProdutoConverter produtoConverter;
    private final ClienteConverter clienteConverter;
    private final ClienteEnderecoConverter clienteEnderecoConverter;
    private final EmpresaConverter empresaConverter;
    private final VendaConverter vendaConverter;
    private final VendaProdutoConverter vendaProdutoConverter;

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.addConverter(produtoConverter.getCreateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getUpdateProdutoToProduto());
        modelMapper.addConverter(produtoConverter.getProdutoToResponseProduto());
        modelMapper.addConverter(produtoConverter.getProdutoToProdutoResumeResponse());

        modelMapper.addConverter(clienteConverter.getCreateClienteToCliente());
        modelMapper.addConverter(clienteConverter.getUpdateClienteToCliente());
        modelMapper.addConverter(clienteConverter.getClienteToResponseCliente());
        modelMapper.addConverter(clienteConverter.getClienteToClienteResumeResponse());

        modelMapper.addConverter(clienteEnderecoConverter.getUpdateClienteEnderecoToClienteEndereco());
        modelMapper.addConverter(clienteEnderecoConverter.getClienteEnderecoToClienteEnderecoResponse());
        modelMapper.addConverter(clienteEnderecoConverter.getUpdateClienteEnderecoToClienteEndereco());

        modelMapper.addConverter(empresaConverter.getCreateEmpresaToEmpresa());
        modelMapper.addConverter(empresaConverter.getUpdateEmpresaToEmpresa());
        modelMapper.addConverter(empresaConverter.getCreateEmpresaToEmpresa());
        modelMapper.addConverter(empresaConverter.getEmpresaToEmpresaResumeResponse());

        modelMapper.addConverter(vendaConverter.getCreateVendaToVenda());
        modelMapper.addConverter(vendaConverter.getVendaToVendaResponse());
        modelMapper.addConverter(vendaConverter.getVendaToVendaDetailsResponse());

        modelMapper.addConverter(vendaProdutoConverter.getVendaProdutoToVendaProdutoResponse());

        return modelMapper;
    }
}
