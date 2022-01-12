package br.com.bagarote.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProdutoRequest {

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    private String produto;


    private String descricao;

    private BigDecimal valor;

    private String imagemProduto;

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    private Long idEmpresa;

    public byte[] getImagemProdutoByte() {
        if(Objects.nonNull(this.imagemProduto))
            return Base64.getDecoder().decode(this.imagemProduto);
        return null;
    }
}
