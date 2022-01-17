package br.com.bagarote.service;

import br.com.bagarote.model.dto.request.CreateVenda;
import br.com.bagarote.model.dto.request.VendaProdutoCreate;
import br.com.bagarote.model.dto.response.EmpresaResumeResponse;
import br.com.bagarote.model.dto.response.VendaDetailsResponse;
import br.com.bagarote.model.dto.response.VendaProdutoResponse;
import br.com.bagarote.model.dto.response.VendaResponse;
import br.com.bagarote.model.entity.*;
import br.com.bagarote.repository.ProdutoRepository;
import br.com.bagarote.repository.VendaProdutoRepository;
import br.com.bagarote.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VendaService {

    private VendaRepository vendaRepository;
    private ModelMapper modelMapper;
    private ProdutoRepository produtoRepository;
    private VendaProdutoRepository vendaProdutoRepository;

    public VendaService(VendaRepository vendaRepository,
                        ModelMapper modelMapper,
                        ProdutoRepository produtoRepository,
                        VendaProdutoRepository vendaProdutoRepository
    ){
        this.vendaRepository = vendaRepository;
        this.modelMapper = modelMapper;
        this.produtoRepository = produtoRepository;
        this.vendaProdutoRepository =vendaProdutoRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class, RuntimeException.class})
    public VendaResponse create(CreateVenda createVenda){

        List<VendaProdutoCreate> vendasProdutos = createVenda.getProdutos();
        Double valorTotal = getValorTotal(vendasProdutos);
        createVenda.setValorTotal(BigDecimal.valueOf(valorTotal));
        Venda venda = modelMapper.map(createVenda,Venda.class);
        Venda vendaSalva = vendaRepository.save(venda);
        VendaResponse response = modelMapper.map(vendaSalva,VendaResponse.class);
        List<VendaProdutoCreate> vendasProdutoCreate = populoarVendaProduto(vendasProdutos,vendaSalva.getIdVenda());
        System.out.println("vendasProdutoCreate size"+vendasProdutoCreate.size());
        saveListVendaProduto(response,vendasProdutoCreate);
        return response;
    }

    private void saveListVendaProduto(VendaResponse venda,List<VendaProdutoCreate> list){
        for(VendaProdutoCreate vpc:list){
            VendaProduto vp = new VendaProduto();
            Venda v = new Venda();
            v.setIdVenda(venda.getIdVenda());
            Produto p =new Produto();
            p.setIdProduto(vpc.getIdProduto());
            VendaProduto.VendaProdutoId vpid = new VendaProduto.VendaProdutoId();
            //vp.getVendaProdutoId().setProduto(Produto.builder().idProduto(vpc.getIdProduto()).build());
            //vp.getVendaProdutoId().setVenda(Venda.builder().idVenda(venda.getIdVenda()).build());
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
        PageImpl vendasPaginadas = new PageImpl<VendaDetailsResponse>(
                result.getContent().stream()
                        .map(venda-> modelMapper.map(venda, VendaDetailsResponse.class))
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


