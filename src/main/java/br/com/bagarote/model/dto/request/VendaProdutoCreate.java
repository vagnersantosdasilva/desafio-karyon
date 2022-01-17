package br.com.bagarote.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoCreate {
    private Long idVenda;
    private Long idProduto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}
