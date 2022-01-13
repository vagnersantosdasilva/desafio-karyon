package br.com.bagarote.model.dto.response;

import lombok.Builder;


@Builder
public class ClienteEnderecoResponse {

    private Long idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Long idCliente;
}
