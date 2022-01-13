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

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    @NotBlank(message = "campoEmBranco")
    @Size(min = 5, max = 50, message = "tamanhoCampo")
    private String nomeFantasia;

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    @NotBlank(message = "campoEmBranco")
    @Size(min = 5, max = 100, message = "tamanhoCampo")
    private String razaoSocial;

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    @NotBlank(message = "campoEmBranco")
    @Size(min = 14, max = 14, message = "tamanhoCampo")
    private String cnpj;

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    @NotBlank(message = "campoEmBranco")
    @Size(min = 5, max = 60, message = "tamanhoCampo")
    private String representanteLegal;

    @NotNull(message = "campoNulo")
    @NotEmpty(message = "campoEmBranco")
    @NotBlank(message = "campoEmBranco")
    @Size(min = 11, max = 11, message = "tamanhoCampo")
    private String telefone;


}
