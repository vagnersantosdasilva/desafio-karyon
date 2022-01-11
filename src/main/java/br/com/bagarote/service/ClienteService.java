package br.com.bagarote.service;

import br.com.bagarote.model.Cliente;
import br.com.bagarote.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Object findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public Cliente save(Cliente createCliente) {
        return clienteRepository.save(createCliente);
    }
}
