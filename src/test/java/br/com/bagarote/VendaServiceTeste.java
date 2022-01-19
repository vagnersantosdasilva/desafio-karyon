package br.com.bagarote;

import br.com.bagarote.exception.BusinessRuleException;
import br.com.bagarote.model.dto.request.CreateVenda;
import br.com.bagarote.model.dto.request.VendaProdutoCreate;
import br.com.bagarote.model.entity.MetodoPagamento;
import br.com.bagarote.service.VendaService;
import br.com.bagarote.util.FileUtil;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaServiceTeste {

    @Autowired
    private VendaService service;

    private CreateVenda venda;

    private void getJSONFile()  {
        this.venda = FileUtil.fileJSONRead("Venda.JSON");
    }

    @Test
    public void testeValidacaoRegrasNegecio() throws Exception{
        getJSONFile();
        Assert.assertEquals(true,service.isValidRoles(venda));
    }

    @Test(expected = BusinessRuleException.class)
    public void testeEmpresaCliente() throws Exception{
        getJSONFile();
        venda.setIdCliente(Long.parseLong("45"));
        service.checkClienteEmpresa(venda.getIdCliente(), venda.getIdEmpresa());
    }

    @Test(expected = BusinessRuleException.class)
    public void testListaProdutoVazia() throws Exception {
        getJSONFile();
        List<VendaProdutoCreate> vp = new ArrayList<>();
        venda.setProdutos(vp);
        service.checkProdutos(venda.getProdutos(), venda.getIdEmpresa());

    }

    @Test(expected = BusinessRuleException.class)
    public void testMetodoPagamentoInexistente() throws Exception {
        getJSONFile();
        service.checkMetodoPagamento("BOLETO");
    }

}
