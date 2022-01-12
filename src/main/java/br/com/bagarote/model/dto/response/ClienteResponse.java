package br.com.bagarote.model.dto.response;

import br.com.bagarote.model.entity.ClienteEndereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private Long idCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private Long idEmpresa;
    private List<ClienteEndereco> enderecos;

}
