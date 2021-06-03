package br.com.bootcamp.controller;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Compra;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.CompraRequest;
import br.com.bootcamp.request.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.BindException;

@RestController
public class FechamentoDeCompraP1Controller {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/compra")
    @Transactional
    public String novaCompra(@RequestBody @Valid CompraRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = entityManager.find(Produto.class, request.getIdProduto());
        int quantidade = request.getQuantidade();
        boolean abatido = produto.abateDoEstoque(quantidade);

        if (abatido) {
            Cliente comprador = clienteRepository.findByEmail("exemplo@exemplo.com.br").get();
            GatewayPagamento gateway = request.getGateway();
            Compra novaCompra = new Compra(produto, quantidade, comprador, gateway);
            entityManager.persist(novaCompra);
            if (gateway.equals(GatewayPagamento.pagseguro)) {
                String urlRetornoPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(novaCompra.getId()).toString();

                return "pagseguro.com?buyerId= " + novaCompra.getId()
                        + "redirectUrl=" + urlRetornoPagSeguro;

            } else {
                String urlRetornoPayPal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com?buyerId= " + novaCompra.getId()
                        + "redirectUrl=" + urlRetornoPayPal;
            }
        }
        BindException semEstoque = new BindException();
        throw semEstoque;
    }
}
