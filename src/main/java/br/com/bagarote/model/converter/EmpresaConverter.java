package br.com.bagarote.model.converter;

import br.com.bagarote.model.dto.request.CreateEmpresa;
import br.com.bagarote.model.dto.request.UpdateEmpresa;
import br.com.bagarote.model.dto.response.EmpresaResponse;
import br.com.bagarote.model.dto.response.EmpresaResumeResponse;
import br.com.bagarote.model.entity.Empresa;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EmpresaConverter {

    Converter<CreateEmpresa, Empresa> createEmpresaToEmpresa = new Converter<CreateEmpresa, Empresa>(){
        @Override
        public Empresa convert(MappingContext<CreateEmpresa, Empresa> mappingContext) {
            return Empresa.builder()
                    .cnpj(mappingContext.getSource().getCnpj())
                    .nomeFantasia(mappingContext.getSource().getNomeFantasia())
                    .razaoSocial(mappingContext.getSource().getRazaoSocial())
                    .telefone(mappingContext.getSource().getTelefone())
                    .responsavelLegal(mappingContext.getSource().getResponsavelLegal())
                    .build();
        }
    };

    Converter<UpdateEmpresa, Empresa> updateEmpresaToEmpresa = new Converter<UpdateEmpresa, Empresa>(){
        @Override
        public Empresa convert(MappingContext<UpdateEmpresa, Empresa> mappingContext) {
            return Empresa.builder()
                    .idEmpresa(mappingContext.getSource().getIdEmpresa())
                    .cnpj(mappingContext.getSource().getCnpj())
                    .nomeFantasia(mappingContext.getSource().getNomeFantasia())
                    .razaoSocial(mappingContext.getSource().getRazaoSocial())
                    .telefone(mappingContext.getSource().getTelefone())
                    .responsavelLegal(mappingContext.getSource().getResponsavelLegal())
                    .build();
        }
    };

    Converter<Empresa, EmpresaResponse> empresaToEmpresaResponse = new Converter<Empresa,EmpresaResponse>(){
        @Override
        public EmpresaResponse convert(MappingContext<Empresa, EmpresaResponse> mappingContext) {
            return EmpresaResponse.builder()
                    .idEmpresa(mappingContext.getSource().getIdEmpresa())
                    .cnpj(mappingContext.getSource().getCnpj())
                    .nomeFantasia(mappingContext.getSource().getNomeFantasia())
                    .razaoSocial(mappingContext.getSource().getRazaoSocial())
                    .telefone(mappingContext.getSource().getTelefone())
                    .responsavelLegal(mappingContext.getSource().getResponsavelLegal())
                    .build();
        }
    };

    Converter<Empresa, EmpresaResumeResponse> empresaToEmpresaResumeResponse = new Converter<Empresa,EmpresaResumeResponse>(){
        @Override
        public EmpresaResumeResponse convert(MappingContext<Empresa, EmpresaResumeResponse> mappingContext) {
            return EmpresaResumeResponse.builder()
                    .idEmpresa(mappingContext.getSource().getIdEmpresa())
                    .nomeFantasia(mappingContext.getSource().getNomeFantasia())
                    .build();
        }
    };
}
