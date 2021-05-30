package br.com.bootcamp.model;

import br.com.bootcamp.request.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @ManyToOne
    @NotNull
    private Categoria idCategoria;

    @ManyToOne
    @NotNull
    private Cliente idCliente;

    LocalDateTime instanteDoCadastro;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade,
                   Collection<CaracteristicaRequest> caracteristicas,
                   String descricao, Categoria idCategoria, Cliente idCliente) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toConverter(this))
                .collect(Collectors.toSet()));
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.idCliente = idCliente;
        this.instanteDoCadastro = LocalDateTime.now();
    }

    public void associaImagem(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                ", idCliente=" + idCliente +
                ", instanteDoCadastro=" + instanteDoCadastro +
                '}';
    }

    public boolean clienteAssociado(Cliente usuarioAssociado) {
        return this.idCliente.equals(usuarioAssociado);
    }
}
