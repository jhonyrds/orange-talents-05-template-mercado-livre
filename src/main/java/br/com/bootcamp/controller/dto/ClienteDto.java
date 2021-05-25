package br.com.bootcamp.controller.dto;
import java.time.LocalDateTime;

public class ClienteDto {
    private Long id;
    private String login;
    private String senha;
    private LocalDateTime instanteCadastro;

    public ClienteDto(Long id, String login, String senha, LocalDateTime instanteCadastro){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }
}
