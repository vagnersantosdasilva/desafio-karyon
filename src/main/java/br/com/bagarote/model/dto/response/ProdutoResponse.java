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
public class ProdutoResponse {

    private Long idProduto;
    private String produto;
    private String descricao;
    private BigDecimal valor;
    private String imagemProduto;
    private Long idEmpresa;
}
