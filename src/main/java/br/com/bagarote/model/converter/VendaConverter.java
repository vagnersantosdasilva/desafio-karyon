package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.request.CreateProduto;
import br.com.bagarote.model.dto.request.CreateVenda;
import br.com.bagarote.model.dto.response.VendaDetailsResponse;
import br.com.bagarote.model.dto.response.VendaResponse;
import br.com.bagarote.model.entity.Cliente;
import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.model.entity.Produto;
import br.com.bagarote.model.entity.Venda;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Component
public class VendaConverter {

    Converter<CreateVenda, Venda> createVendaToVenda = new Converter<CreateVenda, Venda>(){
        @Override
        public Venda convert(MappingContext<CreateVenda, Venda> mappingContext) {
            return Venda.builder()
                    //.produtos(mappingContext.getSource().getProdutos())
                    .dataVenda(mappingContext.getSource().getDataVendaLocalDateTime())
                    .cliente(Objects.nonNull(mappingContext.getSource().getIdCliente()) ? Cliente.builder().idCliente(mappingContext.getSource().getIdCliente()).build() : null )
                    .empresa(Objects.nonNull(mappingContext.getSource().getIdEmpresa()) ? Empresa.builder().idEmpresa(mappingContext.getSource().getIdEmpresa()).build() : null )
                    .metodoPagamento(mappingContext.getSource().getMetodoPagamento())
                    .valorDesconto(mappingContext.getSource().getValorDesconto()!=null?mappingContext.getSource().getValorDesconto(): BigDecimal.ZERO)
                    .valorPago(mappingContext.getSource().getValorPago()!=null?mappingContext.getSource().getValorPago():BigDecimal.ZERO)
                    .valorTotal(mappingContext.getSource().getValorTotal()!=null?mappingContext.getSource().getValorTotal():BigDecimal.ZERO)
                    .valorAcrescimo(mappingContext.getSource().getValorAcrescimo()!=null?mappingContext.getSource().getValorAcrescimo():BigDecimal.ZERO)
                    .build();
        }
    };

    Converter<Venda,VendaResponse> vendaToVendaResponse = new Converter< Venda, VendaResponse>(){
        @Override
        public VendaResponse convert(MappingContext< Venda ,VendaResponse> mappingContext) {
            return VendaResponse.builder()
                    .dataVenda(mappingContext.getSource().getDataVenda())
                    .idVenda(mappingContext.getSource().getIdVenda())
                    .idCliente(mappingContext.getSource().getCliente().getIdCliente())
                    .idEmpresa(mappingContext.getSource().getEmpresa().getIdEmpresa())
                    .metodoPagamento(mappingContext.getSource().getMetodoPagamento().getMetodoPagamento())
                    .nomeCliente(mappingContext.getSource().getCliente().getNome())
                    .nomeFantasia(mappingContext.getSource().getEmpresa().getNomeFantasia())
                    .valorPago(mappingContext.getSource().getValorPago())
                    .valorAcrescimo(mappingContext.getSource().getValorAcrescimo())
                    .valorDesconto(mappingContext.getSource().getValorDesconto())
                    .valorTotal(mappingContext.getSource().getValorTotal())
                    .build();
        }
    };

    Converter<Venda, VendaDetailsResponse> vendaToVendaDetailsResponse = new Converter< Venda, VendaDetailsResponse>(){
        @Override
        public VendaDetailsResponse convert(MappingContext< Venda ,VendaDetailsResponse> mappingContext) {
            return VendaDetailsResponse.builder()
                    .dataVenda(mappingContext.getSource().getDataVenda())
                    .idVenda(mappingContext.getSource().getIdVenda())
                    .idCliente(mappingContext.getSource().getCliente().getIdCliente())
                    .idEmpresa(mappingContext.getSource().getEmpresa().getIdEmpresa())
                    .metodoPagamento(mappingContext.getSource().getMetodoPagamento().getMetodoPagamento())
                    .nomeCliente(mappingContext.getSource().getCliente().getNome())
                    .nomeFantasia(mappingContext.getSource().getEmpresa().getNomeFantasia())
                    .valorPago(mappingContext.getSource().getValorPago())
                    .valorAcrescimo(mappingContext.getSource().getValorAcrescimo())
                    .valorDesconto(mappingContext.getSource().getValorDesconto())
                    .valorTotal(mappingContext.getSource().getValorTotal())
                    .build();
        }
    };
}
