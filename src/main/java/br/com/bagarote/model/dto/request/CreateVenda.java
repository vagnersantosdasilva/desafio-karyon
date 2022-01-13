package br.com.bagarote.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVenda {

    @NotNull(message = "Id de cliente não pode ser nulo")
    private Long idCliente;

    @NotNull(message = "Id de empresa não pode ser nulo")
    private Long idEmpresa;

    @NotNull(message ="A data da venda deve ser informada")
    private LocalDateTime dataVenda;

    @NotNull(message="Valor deve ser informado")
    private BigDecimal valorTotal;

    @NotNull(message="Valor deve ser informado")
    private BigDecimal valorDesconto;

    @NotNull(message="Valor deve ser informado")
    private BigDecimal valorAcrescimo;

    @NotNull(message="Valor deve ser informado")
    private BigDecimal valorPago;

    private String metodoPagamento;



}
