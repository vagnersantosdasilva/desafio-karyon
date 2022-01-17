package br.com.bagarote.model.dto.request;


import br.com.bagarote.model.entity.MetodoPagamento;
import br.com.bagarote.model.entity.VendaProduto;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVenda {

    @NotNull(message = "campoNulo")
    private Long idCliente;

    @NotNull(message = "campoNulo")
    private Long idEmpresa;

    private String dataVenda;
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorAcrescimo;
    private BigDecimal valorPago;
    private String metodoPagamento;
    private List<VendaProdutoCreate> produtos;
    //private List<VendaProduto> produtos;

    public LocalDateTime getDataVendaLocalDateTime(){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.parse(this.dataVenda,parser).toString());
        LocalDateTime dateTime = LocalDateTime.parse(this.dataVenda,parser);
        return  dateTime;
    }

    public MetodoPagamento getMetodoPagamento() {

        if (metodoPagamento.equals("CREDITO")) return MetodoPagamento.CREDITO;
        if (metodoPagamento.equals("PIX")) return  MetodoPagamento.PIX;
        if(metodoPagamento.equals("PENDENTE")) return MetodoPagamento.PENDENTE;
        if (metodoPagamento.equals("DEBITO")) return MetodoPagamento.DEBITO;
        if (metodoPagamento.equals("DINHEIRO")) return MetodoPagamento.DINHEIRO;
        return null;
    }


}
