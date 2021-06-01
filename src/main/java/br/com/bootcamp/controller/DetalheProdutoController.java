package br.com.bootcamp.controller;

import br.com.bootcamp.controller.dto.DetalheProdutoDto;
import br.com.bootcamp.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/cadastro-produto/{id}")
    public DetalheProdutoDto detalheProdutoDto(@PathVariable("id") Long id){
        Produto produto = entityManager.find(Produto.class, id);
        return new DetalheProdutoDto(produto);
    }
}
