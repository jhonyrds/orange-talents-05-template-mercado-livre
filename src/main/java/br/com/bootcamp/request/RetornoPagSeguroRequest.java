package br.com.bootcamp.request;

import br.com.bootcamp.model.Compra;
import br.com.bootcamp.model.Transacao;
import br.com.bootcamp.util.UniqueField;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento{
    @NotBlank
    @UniqueField(entityName = Transacao.class, fieldName = "idTransacaoGateway")
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

    @Override
    public String toString() {
        return "RetornoPagSeguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }
}
