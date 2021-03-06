package br.com.bagarote.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoResponse {

    private Long idVenda;
    private Long idProduto;
    private String produto;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private BigDecimal valorTotal;

}
