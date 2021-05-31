package br.com.bootcamp.controller;


import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.OpiniaoProduto;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.OpiniaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AdicionaOpiniaoController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastro-produto/{id}/opiniao")
    @Transactional
    public String adicionaOpiniao(@RequestBody @Valid OpiniaoRequest request, @PathVariable("id") Long id, Cliente logado){
        Produto produto = entityManager.find(Produto.class, id);
        Cliente cliente = clienteRepository.findByEmail("exemplo@exemplo.com.br").get();
        OpiniaoProduto opiniao = request.converter(produto, cliente);
        entityManager.persist(opiniao);

        return opiniao.toString();
    }
}
