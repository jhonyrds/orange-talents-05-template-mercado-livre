package br.com.bootcamp.model;

import br.com.bootcamp.request.GatewayPagamento;
import br.com.bootcamp.request.RetornoGatewayPagamento;
import br.com.bootcamp.request.RetornoPagSeguroRequest;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Produto produto;

    @Positive
    @NotNull
    private int quantidade;

    @ManyToOne
    @NotNull
    private Cliente comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra(){}

    public Compra(Produto produto, int quantidade, Cliente comprador, GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getId() {
        return id;
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao transacao = request.toTransacao(this);

        Assert.state(transacoesConcluidas().isEmpty(), "A Compra já foi concluída");

        this.transacoes.add(transacao);
    }

    private Set<Transacao> transacoesConcluidas() {
        Set<Transacao> transacoesConcluidas = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidas.size() <= 1, "Existe mais de uma transação concluída" + id);

        return transacoesConcluidas;
    }
}
