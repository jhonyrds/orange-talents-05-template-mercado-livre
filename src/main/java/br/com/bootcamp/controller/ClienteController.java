package br.com.bootcamp.controller;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.request.ClienteRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/cadastro-cliente")
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid ClienteRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Cliente cliente = request.converter(entityManager);
        entityManager.persist(cliente);
        return cliente.toString();
    }
}
