package br.com.bagarote.service;

import br.com.bagarote.exception.BusinessRuleException;
import br.com.bagarote.model.dto.request.CreateVenda;
import br.com.bagarote.model.dto.request.CreateVendaProduto;
import br.com.bagarote.model.dto.request.VendaProdutoCreate;
import br.com.bagarote.model.dto.response.VendaDetailsResponse;
import br.com.bagarote.model.dto.response.VendaProdutoResponse;
import br.com.bagarote.model.dto.response.VendaResponse;
import br.com.bagarote.model.entity.*;
import br.com.bagarote.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class VendaService {

    private VendaRepository vendaRepository;
    private ModelMapper modelMapper;
    private ProdutoRepository produtoRepository;
    private VendaProdutoRepository vendaProdutoRepository;
    private EmpresaRepository empresaRepository;
    private ClienteRepository clienteRepository;

    public VendaService(@Lazy VendaRepository vendaRepository,
                        ModelMapper modelMapper,
                        @Lazy ProdutoRepository produtoRepository,
                        @Lazy VendaProdutoRepository vendaProdutoRepository,
                        @Lazy EmpresaRepository empresaRepository, ClienteRepository clienteRepository){

        this.vendaRepository = vendaRepository;
        this.modelMapper = modelMapper;
        this.produtoRepository = produtoRepository;
        this.vendaProdutoRepository =vendaProdutoRepository;
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class, RuntimeException.class})
    public VendaResponse create(CreateVenda createVenda) throws BusinessRuleException {
        if (isValidRoles(createVenda)) {
            List<VendaProdutoCreate> vendasProdutos = createVenda.getProdutos();
            Double valorTotal = getValorTotal(vendasProdutos);
            createVenda.setValorTotal(BigDecimal.valueOf(valorTotal));
            Venda venda = modelMapper.map(createVenda, Venda.class);
            Venda vendaSalva = vendaRepository.save(venda);
            VendaResponse response = modelMapper.map(vendaSalva, VendaResponse.class);
            List<VendaProdutoCreate> vendasProdutoCreate = populoarVendaProduto(vendasProdutos, vendaSalva.getIdVenda());
            System.out.println("vendasProdutoCreate size" + vendasProdutoCreate.size());
            saveListVendaProduto(response, vendasProdutoCreate);
            return response;
        }
        return null;
    }

    private void saveListVendaProduto(VendaResponse venda,List<VendaProdutoCreate> list){
        for(VendaProdutoCreate vpc:list){
            VendaProduto vp = new VendaProduto();

            Venda v = new Venda();
            v.setIdVenda(venda.getIdVenda());
            Produto p =new Produto();
            p.setIdProduto(vpc.getIdProduto());

            VendaProduto.VendaProdutoId vpid = new VendaProduto.VendaProdutoId();
            vpid.setVenda(v);
            vpid.setProduto(p);

            vp.setVendaProdutoId(vpid);
            vp.setValorTotal(vpc.getValorTotal());
            vp.setQtd(vpc.getQuantidade());
            vp.setValorUnitario(vpc.getValorUnitario());

            System.out.println("ID Venda :" +vp.getVendaProdutoId().getVenda().getIdVenda());
            System.out.println("ID Produ :" +vp.getVendaProdutoId().getProduto().getIdProduto());

            vendaProdutoRepository.save(vp);
        }


    }

    public Boolean isValidRoles(CreateVenda venda) throws BusinessRuleException {
        checkClienteEmpresa(venda.getIdCliente(),venda.getIdEmpresa());
        checkProdutos(venda.getProdutos(),venda.getIdEmpresa());
        checkMetodoPagamento(venda.getMetodoPagamento().getMetodoPagamento());

        return true;
    }

    public void checkClienteEmpresa(Long idCliente,Long idEmpresa) throws BusinessRuleException {
        try{
            Cliente cliente = clienteRepository.getById(idCliente);
            if ((cliente ==null)||
                    (!cliente.getEmpresa().getIdEmpresa().equals(idEmpresa)))
                throw new BusinessRuleException("Erro de regra de negócio. Cliente não pertence a empresa.");
        }
        catch(EntityNotFoundException ex){
            throw new BusinessRuleException("Erro de regra de negócio. Cliente informado não existe.");
        }

    }

    public void checkProdutos(List<VendaProdutoCreate> produtos,Long idEmpresa) throws BusinessRuleException {

        if (produtos.size()<=0)
            throw new BusinessRuleException("Erro de regra de negócio. Lista de produto não pode ser vazia.");

        for (VendaProdutoCreate vpc : produtos){
            Produto p = produtoRepository.findById(vpc.getIdProduto()).orElse(null);
            if (p ==null) throw new BusinessRuleException("Produto de ID "+vpc.getIdProduto()+" não existe. ");
            if (!p.getEmpresa().getIdEmpresa().equals(idEmpresa))
                throw new BusinessRuleException("Erro de regra de negócio. Produto não pertence a empresa");
            if (vpc.getQuantidade() <=0) throw new BusinessRuleException("Erro de regra de negócio. Quantidade de um produto deve ser maior que zero");
        }
    }

    public void checkMetodoPagamento(String metodo) throws BusinessRuleException {
        if (metodo==null)
            throw new BusinessRuleException("Erro de regra de negócios. Método de pagamento inexistente");
        try
        {
            MetodoPagamento.valueOf(metodo);
        }
        catch(IllegalArgumentException ix) {
            throw new BusinessRuleException("Erro de regra de negócios. Método de pagamento inexistente");
        }
    }

    private Double getValorTotal (List<VendaProdutoCreate> produtos){
        Double valorTotal = 0.0;
        for (VendaProdutoCreate v : produtos){
            Produto p = produtoRepository.findById(v.getIdProduto()).orElse(null);
            if (p!=null) {
                BigDecimal valor = p.getValorBase().multiply(BigDecimal.valueOf(v.getQuantidade()));
                valorTotal = valorTotal + valor.doubleValue();
            }
        }
        return valorTotal;
    }

    private List<VendaProdutoCreate> populoarVendaProduto (List<VendaProdutoCreate> produtos,Long idVenda){
        List<VendaProdutoCreate> vendasProduto = new ArrayList<>();
        for (VendaProdutoCreate v : produtos){
            Produto p = produtoRepository.findById(v.getIdProduto()).orElse(null);
            if (p!=null) {
                VendaProdutoCreate vendaProdutoCreate = VendaProdutoCreate.builder()
                        .idVenda(idVenda)
                        .idProduto(p.getIdProduto())
                        .valorUnitario(p.getValorBase())
                        .valorTotal(p.getValorBase().multiply(BigDecimal.valueOf(v.getQuantidade())))
                        .quantidade(v.getQuantidade())
                        .build();
                vendasProduto.add(vendaProdutoCreate);
            }

        }
        return vendasProduto;
    }


    public Venda findById(Long idVenda) {
        return vendaRepository.findById(idVenda).orElse(null);
    }

    public PageImpl vendaByClienteAndEnpresa(Pageable pageable, Long idCliente, Long idEmpresa){
        Page<Venda> result = vendaRepository.findVendaByClienteEmpresa(idCliente,idEmpresa,pageable);
        PageImpl vendasPaginadas = new PageImpl<VendaResponse>(
                result.getContent().stream()
                        .map(venda-> modelMapper.map(venda, VendaResponse.class))
                        .collect(Collectors.toList()), pageable, result.getTotalElements());
        return vendasPaginadas;

    }

    public Object vendaByIdVendaIdEmpresa(Long idVenda, Long idEmpresa){

        VendaDetailsResponse vendaResponse  = modelMapper.map(
                vendaRepository.findByIdVendaIdEmpresa(idVenda,idEmpresa),VendaDetailsResponse.class);


        List<VendaProdutoResponse> result = vendaProdutoRepository.findByIdVenda(idVenda).stream()
                .map(vp-> modelMapper.map(vp,VendaProdutoResponse.class))
                .collect(Collectors.toList());

        vendaResponse.setProdutos(result);

        return vendaResponse;
    }
}


