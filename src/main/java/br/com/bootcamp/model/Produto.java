package br.com.bootcamp.model;

import br.com.bootcamp.request.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<OpiniaoProduto> opinioes = new HashSet<>();

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

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricaoProduto() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return this.caracteristicas;
    }

    public <T> Set<T> mapCaracteristicas(
            Function<CaracteristicaProduto, T> funcaoMap) {
        return this.caracteristicas.stream().map(funcaoMap)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(
            Function<ImagemProduto, T> funcaoMap) {
        return this.imagens.stream().map(funcaoMap)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapPerguntas(
            Function<Pergunta, T> funcaoMap) {
        return this.perguntas.stream().map(funcaoMap)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public <T> Set<T> mapOpinioes(
            Function<OpiniaoProduto, T> funcaoMap) {
        return this.opinioes.stream().map(funcaoMap)
                .collect(Collectors.toSet());
    }

    public boolean clienteAssociado(Cliente usuarioAssociado) {
        return this.idCliente.equals(usuarioAssociado);
    }

    public boolean abateDoEstoque(@Positive int quantidade) {
        if(quantidade <= this.quantidade){
            this.quantidade-=quantidade;
            return true;
        }
        return false;
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
}
