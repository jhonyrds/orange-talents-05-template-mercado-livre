package br.com.bootcamp.request;

import br.com.bootcamp.model.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {
    @NotBlank
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converter(EntityManager entityManager){
            Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null){
            Categoria categoriaMae = entityManager.find(Categoria.class, idCategoriaMae);
            Assert.notNull(categoriaMae, "Id da Categoria precisa ser válido!");
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }
}
