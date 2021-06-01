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

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpiniaoProduto that = (OpiniaoProduto) o;

        if (nota != that.nota) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        return cliente != null ? cliente.equals(that.cliente) : that.cliente == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + nota;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        return result;
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
