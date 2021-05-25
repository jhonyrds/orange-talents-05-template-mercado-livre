package br.com.bootcamp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email @NotBlank
    private String login;
    @NotBlank @Size(min = 6)
    private String senha;
    private LocalDateTime instanteCadastro;

    @Deprecated
    public Cliente(){}

    public Cliente(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = LocalDateTime.now();
    }
}
