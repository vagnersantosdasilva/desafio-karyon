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
public class UpdateProduto {

    @NotNull(message = "Campo idProduto não pode ser nulo. ")
    private Long idProduto;

    @NotNull(message = "Campo produto não pode ser nulo. ")
    @NotEmpty(message = "Campo produto não pode ser vazio. ")
    private String produto;

    private String descricao;

    private BigDecimal valor;

    private String imagemProduto;

    @NotNull(message = "Campo idEmpresa não pode ser nulo. ")
    private Long idEmpresa;

    public byte[] getImagemProdutoByte() {
        if(Objects.nonNull(this.imagemProduto))
            return Base64.getDecoder().decode(this.imagemProduto);
        return null;
    }

}
