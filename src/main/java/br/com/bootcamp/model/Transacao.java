package br.com.bootcamp.model;

import br.com.bootcamp.request.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacaoGateway;

    @ManyToOne
    @NotNull
    private Compra compra;

    private LocalDateTime instante;

    @Deprecated
    public Transacao(){}

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

}
