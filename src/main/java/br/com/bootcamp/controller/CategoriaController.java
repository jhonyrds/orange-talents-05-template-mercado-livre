package br.com.bootcamp.controller;

import br.com.bootcamp.model.Categoria;
import br.com.bootcamp.request.CategoriaRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public String cadastroCategoria(@RequestBody @Valid CategoriaRequest request){
        Categoria categoria = request.converter(entityManager);
        entityManager.persist(categoria);
        return categoria.toString();
    }
}
