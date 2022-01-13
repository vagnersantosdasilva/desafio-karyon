package br.com.bagarote.service;

import br.com.bagarote.model.dto.response.ClienteResumeResponse;
import br.com.bagarote.model.dto.response.EmpresaResumeResponse;
import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public Empresa findById(Long idEmpresa) {

        return empresaRepository.findById(idEmpresa).orElse(null);
    }

    public Empresa save(Empresa createEmpresa) {
        return empresaRepository.save(createEmpresa);
    }
}
