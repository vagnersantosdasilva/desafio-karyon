package br.com.bagarote.service;

import br.com.bagarote.exception.BusinessRuleException;
import br.com.bagarote.model.dto.request.CreateEmpresa;
import br.com.bagarote.model.dto.request.UpdateEmpresa;
import br.com.bagarote.model.dto.response.ClienteResumeResponse;
import br.com.bagarote.model.dto.response.EmpresaResponse;
import br.com.bagarote.model.dto.response.EmpresaResumeResponse;
import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository, ModelMapper modelMapper){
        this.empresaRepository = empresaRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmpresaResumeResponse> findAll() {
        return empresaRepository.findAll()
                .stream()
                .map(empresa-> modelMapper.map(empresa, EmpresaResumeResponse.class))
                .collect(Collectors.toList());
    }

    public EmpresaResponse findById(Long idEmpresa) {

        return modelMapper.map(empresaRepository.findById(idEmpresa).orElse(null),EmpresaResponse.class);
    }

    public EmpresaResponse create(CreateEmpresa createEmpresa){
        Empresa empresa = empresaRepository.save(modelMapper.map(createEmpresa,Empresa.class));
       return modelMapper.map(empresa,EmpresaResponse.class);
    }

    public EmpresaResponse update(Long idEmpresa, UpdateEmpresa updateEmpresa) throws BusinessRuleException {
        if (updateEmpresa.getIdEmpresa()!=idEmpresa)
            throw new BusinessRuleException("ID de objeto diferente de ID de url");
        Empresa empresa = empresaRepository.save(modelMapper.map(updateEmpresa,Empresa.class));
        return modelMapper.map(empresa,EmpresaResponse.class);

    }

}
