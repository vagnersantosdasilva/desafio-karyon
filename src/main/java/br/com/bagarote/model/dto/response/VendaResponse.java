package br.com.bagarote.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaResponse {

    private Long idVenda;
    private Long idCliente;
    private Long idEmpresa;
    private String nomeFantasia;
    private String nomeCliente;
    private LocalDateTime dataVenda;
    private String metodoPagamento;
    private BigDecimal valorDesconto;
    private BigDecimal valorAcrescimo;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;

}
