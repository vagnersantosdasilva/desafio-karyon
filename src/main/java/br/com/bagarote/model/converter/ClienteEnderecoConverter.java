package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.request.CreateClienteEndereco;
import br.com.bagarote.model.dto.request.UpdateClienteEndereco;
import br.com.bagarote.model.dto.response.ClienteEnderecoResponse;
import br.com.bagarote.model.entity.Cliente;
import br.com.bagarote.model.entity.ClienteEndereco;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Getter
@Component
public class ClienteEnderecoConverter {

    Converter<CreateClienteEndereco, ClienteEndereco> createClienteEnderecoToClienteEndereco = new Converter<CreateClienteEndereco, ClienteEndereco>(){
        @Override
        public ClienteEndereco convert(MappingContext<CreateClienteEndereco, ClienteEndereco> mappingContext) {
            return ClienteEndereco.builder()
                    .logradouro(mappingContext.getSource().getLogradouro())
                    .bairro(mappingContext.getSource().getBairro())
                    .cidade(mappingContext.getSource().getCidade())
                    .uf(mappingContext.getSource().getUf())
                    .numero(mappingContext.getSource().getNumero())
                    .complemento(mappingContext.getSource().getComplemento())
                    .cep(mappingContext.getSource().getCep())
                    .cliente(Objects.nonNull(mappingContext.getSource().getCliente()) ? Cliente.builder().idCliente(mappingContext.getSource().getCliente().getIdCliente()).build() : null )
                    .build();
        }
    };

    Converter<UpdateClienteEndereco, ClienteEndereco> updateClienteEnderecoToClienteEndereco = new Converter<UpdateClienteEndereco, ClienteEndereco>(){
        @Override
        public ClienteEndereco convert(MappingContext<UpdateClienteEndereco, ClienteEndereco> mappingContext) {
            return ClienteEndereco.builder()
                    .idEndereco(mappingContext.getSource().getIdEndereco())
                    .logradouro(mappingContext.getSource().getLogradouro())
                    .bairro(mappingContext.getSource().getBairro())
                    .cidade(mappingContext.getSource().getCidade())
                    .uf(mappingContext.getSource().getUf())
                    .numero(mappingContext.getSource().getNumero())
                    .complemento(mappingContext.getSource().getComplemento())
                    .cep(mappingContext.getSource().getCep())
                    .cliente(Objects.nonNull(mappingContext.getSource().getIdCliente()) ? Cliente.builder().idCliente(mappingContext.getSource().getIdCliente()).build() : null )
                    .build();
        }
    };

    Converter<ClienteEndereco, ClienteEnderecoResponse> clienteEnderecoToClienteEnderecoResponse = new Converter<ClienteEndereco,ClienteEnderecoResponse>(){
        @Override
        public ClienteEnderecoResponse convert(MappingContext<ClienteEndereco,ClienteEnderecoResponse> mappingContext) {
            return ClienteEnderecoResponse.builder()
                    .idEndereco(mappingContext.getSource().getIdEndereco())
                    .logradouro(mappingContext.getSource().getLogradouro())
                    .bairro(mappingContext.getSource().getBairro())
                    .cidade(mappingContext.getSource().getCidade())
                    .uf(mappingContext.getSource().getUf())
                    .numero(mappingContext.getSource().getNumero())
                    .complemento(mappingContext.getSource().getComplemento())
                    .cep(mappingContext.getSource().getCep())
                    .idCliente(mappingContext.getSource().getCliente().getIdCliente())
                    .build();
        }
    };
}
