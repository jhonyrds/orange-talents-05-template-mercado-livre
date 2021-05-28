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
    public Produto(){
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Collection<CaracteristicaRequest> caracteristicas,
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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public LocalDateTime getInstanteDoCadastro() {
        return instanteDoCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return caracteristicas.equals(produto.caracteristicas);
    }

    @Override
    public int hashCode() {
        return caracteristicas.hashCode();
    }
}
