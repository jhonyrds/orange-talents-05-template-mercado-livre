package br.com.bootcamp.model;

import br.com.bootcamp.request.GatewayPagamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway" + gatewayPagamento +
                '}';
    }
}
