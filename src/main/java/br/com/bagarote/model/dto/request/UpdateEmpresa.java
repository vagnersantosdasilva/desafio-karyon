package br.com.bagarote.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmpresa {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id de Empresa não pode ser nulo. ")
    private Long idEmpresa;

    @NotNull(message = "campo Nulo")
    @NotEmpty(message = "campo Em Branco")
    @NotBlank(message = "campo Em Branco")
    @Size(min = 5, max = 50,  message = "Tamanho de campo incorreto")
    private String nomeFantasia;

    @NotNull(message = "campo Nulo")
    @NotEmpty(message = "campo Em Branco")
    @NotBlank(message = "campo Em Branco")
    @Size(min = 5, max = 100,  message = "Tamanho de campo incorreto")
    private String razaoSocial;

    @NotNull(message = "campo Nulo")
    @NotEmpty(message = "campo Em Branco")
    @NotBlank(message = "campo Em Branco")
    @Size(min = 11, max = 11,  message = "Tamanho de campo incorreto")
    private String cnpj;

    @NotNull(message = "campo Nulo")
    @NotEmpty(message = "campo Em Branco")
    @NotBlank(message = "campo Em Branco")
    @Size(min = 5, max = 60, message = "tamanhoCampo")
    private String responsavelLegal;

    @NotNull(message = "campo Nulo")
    @NotEmpty(message = "campo Em Branco")
    @NotBlank(message = "campo Em Branco")
    @Size(min = 11, max = 11,  message = "Tamanho de campo incorreto")
    private String telefone;


}
