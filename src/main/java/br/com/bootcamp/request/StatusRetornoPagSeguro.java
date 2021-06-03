package br.com.bootcamp.request;

public enum StatusRetornoPagSeguro {
    SUCESSO,
    ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
