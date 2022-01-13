package br.com.bagarote.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResumeResponse {

    private Long idEmpresa;
    private String nomeFantasia;

}
