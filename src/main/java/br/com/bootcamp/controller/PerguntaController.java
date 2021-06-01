package br.com.bootcamp.controller;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Pergunta;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.PerguntaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastro-produto/{id}/pergunta")
    @Transactional
    public ResponseEntity novaPergunta(@RequestBody @Valid PerguntaRequest request, @PathVariable("id") Long id){
        Produto produto = entityManager.find(Produto.class, id);
        Cliente cliente = clienteRepository.findByEmail("exemplo@exemplo.com.br").get();
        if(produto != null) {
            Pergunta pergunta = request.converter(produto, cliente);
            entityManager.persist(pergunta);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
