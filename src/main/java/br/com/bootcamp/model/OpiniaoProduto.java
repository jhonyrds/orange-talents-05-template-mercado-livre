package br.com.bootcamp.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Max(5)
    @NotNull
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @ManyToOne()
    @NotNull
    private Produto produto;

    @ManyToOne()
    @NotNull
    private Cliente cliente;


    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(int nota, String titulo, String descricao, Produto produto, Cliente cliente) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.cliente = cliente;

    }

    @Override
    public String toString() {
        return "OpiniaoProduto{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", cliente=" + cliente +
                '}';
    }
}
