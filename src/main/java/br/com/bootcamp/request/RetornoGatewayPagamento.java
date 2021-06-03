package br.com.bootcamp.request;

import br.com.bootcamp.model.Compra;
import br.com.bootcamp.model.Transacao;

public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}
