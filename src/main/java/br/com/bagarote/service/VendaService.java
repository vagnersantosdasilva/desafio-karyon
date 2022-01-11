package br.com.bagarote.service;

import br.com.bagarote.model.Venda;
import br.com.bagarote.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }

    public Object findAll() {
        return vendaRepository.findAll();
    }

    public Venda save(Venda createVendaRequest) {
        return vendaRepository.save(createVendaRequest);
    }

    public Venda findById(Long idVenda) {
        return vendaRepository.findById(idVenda).orElse(null);
    }
}
