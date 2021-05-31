package br.com.bootcamp.request;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.model.Pergunta;
import br.com.bootcamp.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    @JsonCreator
    public PerguntaRequest(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "PerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }

    public Pergunta converter(Produto produto, Cliente cliente) {
        return new Pergunta(titulo, produto, cliente);
    }
}
