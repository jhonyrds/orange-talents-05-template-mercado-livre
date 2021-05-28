package br.com.bootcamp.controller;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.ProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cadastro-produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoRequest request){

        Cliente logado = clienteRepository.findByEmail("jhony.o_rodrigues@hotmail.com").get();
        Produto produto = request.converter(entityManager, logado);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }
}
