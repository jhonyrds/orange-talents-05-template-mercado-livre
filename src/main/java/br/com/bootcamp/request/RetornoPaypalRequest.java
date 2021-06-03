package br.com.bootcamp.request;

import br.com.bootcamp.model.Compra;
import br.com.bootcamp.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento{

    @NotBlank
    private String idTransacao;

    @Min(0)
    @Max(1)
    private int status;


    public RetornoPaypalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusGerado = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(statusGerado, idTransacao, compra);
    }

    @Override
    public String toString() {
        return "RetornoPaypalRequest{" +
                "status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                '}';
    }
}
