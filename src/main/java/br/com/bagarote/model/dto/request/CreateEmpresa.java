package br.com.bagarote.model.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmpresa {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Nome fantasia nulo")
    @Size(min = 5, max = 50, message = "Tamanho de campo incorreto")
    private String nomeFantasia;

    @NotNull(message = "Campo Nulo")
    @Size(min = 5, max = 100,  message = "Tamanho de campo incorreto")
    private String razaoSocial;

    @NotNull(message = "Campo Nulo")
    @Size(min = 11, max = 11,  message = "Tamanho de campo incorreto")
    private String cnpj;

    @NotNull(message = "Campo Nulo")
    @Size(min = 5, max = 60,  message = "Tamanho de campo incorreto")
    private String responsavelLegal;

    @NotNull(message = "campoNulo")
    @Size(min = 11, max = 11,  message = "Tamanho de campo incorreto")
    private String telefone;


}
