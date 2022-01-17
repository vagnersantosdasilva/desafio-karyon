package br.com.bagarote.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaDetailsResponse {

    private Long idVenda;
    private Long idEmpresa;
    private String nomeFantasia;
    private Long idCliente;
    private String nomeCliente;
    private LocalDateTime dataVenda;
    private String metodoPagamento;
    private BigDecimal valorDesconto;
    private BigDecimal valorAcrescimo;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    private List<VendaProdutoResponse> produtos;
}
