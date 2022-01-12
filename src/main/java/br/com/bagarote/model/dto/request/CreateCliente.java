package br.com.bagarote.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCliente {

    @NotNull(message = "Nome de cliente não pode ser nulo. ")
    @NotEmpty(message = "Campo nome de cliente não pode ser em branco. ")
    private String nome;

    @NotNull(message = "CPF não pode ser nulo. ")
    @NotEmpty(message = "CPF não pode ser em branco. ")
    @Size(min = 11, max = 11, message = "Tamanho de CPF é inválido. ")
    private String cpf;

    @Size(min = 10, max = 11, message = "Tamanho de telefone é inválido")
    private String telefone;

    @NotNull(message = "campoNulo")
    private Long idEmpresa;


}
