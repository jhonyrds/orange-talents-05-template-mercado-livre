package br.com.bootcamp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    private LocalDateTime instante;

    @ManyToOne()
    @NotNull
    private Produto produto;

    @ManyToOne()
    @NotNull
    private Cliente cliente;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Produto produto, Cliente cliente){
        this.titulo = titulo;
        this.instante = LocalDateTime.now();
        this.produto = produto;
        this.cliente = cliente;
    }
}
