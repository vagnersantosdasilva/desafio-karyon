package br.com.bagarote.service;

import br.com.bagarote.model.entity.Empresa;
import br.com.bagarote.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public Object findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa).orElse(null);
    }

    public Empresa save(Empresa createEmpresa) {
        return empresaRepository.save(createEmpresa);
    }
}
