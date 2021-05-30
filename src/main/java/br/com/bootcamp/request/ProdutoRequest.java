package br.com.bootcamp.request;

import br.com.bootcamp.model.Categoria;
import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Produto;
import br.com.bootcamp.util.ExisteId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    private List<ImagemRequest> fotos = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExisteId(entidade = Categoria.class, atributo = "id")
    private Long idCategoria;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade,
                          List<CaracteristicaRequest> caracteristicas, List<ImagemRequest> fotos,
                          String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
        this.fotos.addAll(fotos);
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public List<ImagemRequest> getFotos(){return fotos;}

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", fotos=" + fotos +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

        public Produto converter(EntityManager entityManager, Cliente logado){
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria, logado);
    }
}
