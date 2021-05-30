package br.com.bootcamp.controller;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.ImagemRequest;
import br.com.bootcamp.request.ProdutoRequest;
import br.com.bootcamp.request.UploaderSimulado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/cadastro-produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UploaderSimulado uploaderSimulado;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoRequest request) {

        Cliente logado = clienteRepository.findByEmail("exemplo@exemplo.com.br").get();
        Produto produto = request.converter(entityManager, logado);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable("id") Long id, @Valid ImagemRequest request) {

        Cliente logado = clienteRepository.findByEmail("exemplo@exemplo.com.br").get();
        Produto produto = entityManager.find(Produto.class, id);
        if(!produto.clienteAssociado(logado)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderSimulado.enviar(request.getImagens());
        produto.associaImagem(links);
        entityManager.merge(produto);
        return produto.toString();
    }
}
