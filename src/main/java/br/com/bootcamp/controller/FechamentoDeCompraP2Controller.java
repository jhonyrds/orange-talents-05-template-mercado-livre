package br.com.bootcamp.controller;

import br.com.bootcamp.model.Compra;
import br.com.bootcamp.request.RetornoGatewayPagamento;
import br.com.bootcamp.request.RetornoPagSeguroRequest;
import br.com.bootcamp.request.RetornoPaypalRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoDeCompraP2Controller {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public void processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
        processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public void processamentoPayPal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        processa(idCompra, request);
    }

    private void processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = entityManager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        entityManager.merge(compra);
    }

}
