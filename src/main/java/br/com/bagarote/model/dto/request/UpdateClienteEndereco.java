package br.com.bagarote.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClienteEndereco {

    @NotNull(message="Id de endereco não pode ser nulo")
    private Long idEndereco;

    @NotNull(message="CEP não pode ser nulo")
    @Size(min = 10, max = 11, message = "Tamanho de CEP é inválido")
    private String cep;

    @NotNull(message="logradouro não pode ser nulo")
    private String logradouro;

    @NotNull(message="numero não pode ser nulo")
    private String numero;

    private String complemento;

    @NotNull(message="bairro não pode ser nulo")
    private String bairro;

    @NotNull(message="cidade não pode ser nulo")
    private String cidade;

    @NotNull(message="uf não pode ser nulo")
    private String uf;

    @NotNull(message = "idCliente não pode ser nulo")
    private Long idCliente;
}
