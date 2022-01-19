package br.com.bagarote.service;

import br.com.bagarote.exception.ResourceNotFoundException;
import br.com.bagarote.model.dto.request.CreateCliente;
import br.com.bagarote.model.dto.request.UpdateCliente;
import br.com.bagarote.model.dto.response.ClienteResponse;
import br.com.bagarote.model.dto.response.ClienteResumeResponse;
import br.com.bagarote.model.dto.response.ProdutoResponse;
import br.com.bagarote.model.entity.Cliente;
import br.com.bagarote.model.entity.Produto;
import br.com.bagarote.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper){
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClienteResumeResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente-> modelMapper.map(cliente, ClienteResumeResponse.class))
                .collect(Collectors.toList());
    }

    public ClienteResponse findById(Long idCliente) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente ==null) throw new ResourceNotFoundException("ID de Cliente não encontrado! ");
        return modelMapper.map(cliente,ClienteResponse.class);
    }

    public ClienteResponse update(Long idCliente, UpdateCliente clienteUpdate){
        Cliente cliente = modelMapper.map(clienteUpdate, Cliente.class);
        ClienteResponse response = modelMapper.map(clienteRepository.save(cliente),ClienteResponse.class);
        return response ;

    }
    //TODO:Verificar se id de url é igual a id do body
    public ClienteResponse create(CreateCliente clienteCreate){
        Cliente cliente = modelMapper.map(clienteCreate, Cliente.class);
        ClienteResponse response = modelMapper.map(clienteRepository.save(cliente),ClienteResponse.class);
        return response;
    }


}
