package br.com.bootcamp.request;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.OpiniaoProduto;
import br.com.bootcamp.model.Produto;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @Positive
    @Max(5)
    @NotNull
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public OpiniaoProduto converter(Produto produto, Cliente cliente) {
        return new OpiniaoProduto(nota, titulo, descricao, produto, cliente);
    }

}
