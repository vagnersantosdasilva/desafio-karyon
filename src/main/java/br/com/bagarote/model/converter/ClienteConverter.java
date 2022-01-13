package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.request.CreateCliente;
import br.com.bagarote.model.dto.request.UpdateCliente;
import br.com.bagarote.model.dto.response.ClienteResponse;
import br.com.bagarote.model.dto.response.ClienteResumeResponse;
import br.com.bagarote.model.entity.Cliente;
import br.com.bagarote.model.entity.Empresa;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Getter
@Component
public class ClienteConverter {
    Converter<CreateCliente, Cliente> createClienteToCliente = new Converter<CreateCliente, Cliente>(){
        @Override
        public Cliente convert(MappingContext<CreateCliente, Cliente> mappingContext) {
            return Cliente.builder()
                    .empresa( Objects.nonNull(mappingContext.getSource().getIdEmpresa()) ? Empresa.builder().idEmpresa(mappingContext.getSource().getIdEmpresa()).build() : null )
                    .cpf(mappingContext.getSource().getCpf())
                    .telefone(mappingContext.getSource().getTelefone())
                    .nome(mappingContext.getSource().getNome())
                    .build();
        }
    };

    Converter<Cliente, ClienteResponse> ClienteToResponseCliente = new Converter< Cliente,ClienteResponse>(){
        @Override
        public ClienteResponse convert(MappingContext<Cliente, ClienteResponse> mappingContext) {
            return ClienteResponse.builder()
                    .idCliente(mappingContext.getSource().getIdCliente())
                    .cpf(mappingContext.getSource().getCpf())
                    .nome(mappingContext.getSource().getNome())
                    .telefone(mappingContext.getSource().getTelefone())
                    .enderecos(mappingContext.getSource().getEnderecos())
                    .idEmpresa(mappingContext.getSource().getEmpresa().getIdEmpresa())
                    .build();
        }
    };

    Converter<UpdateCliente, Cliente> updateClienteToCliente = new Converter<UpdateCliente, Cliente>(){
        @Override
        public Cliente convert(MappingContext<UpdateCliente, Cliente> mappingContext) {
            return Cliente.builder()
                    .idCliente(mappingContext.getSource().getIdCliente())
                    .empresa( Objects.nonNull(mappingContext.getSource().getIdEmpresa()) ? Empresa.builder().idEmpresa(mappingContext.getSource().getIdEmpresa()).build() : null )
                    .cpf(mappingContext.getSource().getCpf())
                    .telefone(mappingContext.getSource().getTelefone())
                    .nome(mappingContext.getSource().getNome())
                    .build();
        }
    };

    Converter<Cliente, ClienteResumeResponse> clienteToClienteResumeResponse = new Converter< Cliente,ClienteResumeResponse>(){
        @Override
        public ClienteResumeResponse convert(MappingContext<Cliente, ClienteResumeResponse> mappingContext) {
            return ClienteResumeResponse.builder()
                    .idCliente(mappingContext.getSource().getIdCliente())
                    .nome(mappingContext.getSource().getNome())
                    .build();
        }
    };

}
