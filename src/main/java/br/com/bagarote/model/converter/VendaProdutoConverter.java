package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.response.VendaProdutoResponse;
import br.com.bagarote.model.entity.VendaProduto;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VendaProdutoConverter {

    Converter<VendaProduto, VendaProdutoResponse> vendaProdutoToVendaProdutoResponse = new Converter< VendaProduto, VendaProdutoResponse>(){
        @Override
        public VendaProdutoResponse convert(MappingContext< VendaProduto ,VendaProdutoResponse> mappingContext) {
            return VendaProdutoResponse.builder()
                    .idProduto(mappingContext.getSource().getVendaProdutoId().getProduto().getIdProduto())
                    .idVenda(mappingContext.getSource().getVendaProdutoId().getVenda().getIdVenda())
                    .produto(mappingContext.getSource().getVendaProdutoId().getProduto().getProduto())
                    .quantidade(mappingContext.getSource().getQtd())
                    .valorUnitario(mappingContext.getSource().getValorUnitario())
                    .valorTotal(mappingContext.getSource().getValorTotal())
                    .build();
        }
    };
}
