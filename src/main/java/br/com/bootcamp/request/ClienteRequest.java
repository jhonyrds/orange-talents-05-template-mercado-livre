package br.com.bootcamp.request;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.util.UniqueField;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ClienteRequest {
    @Email
    @NotBlank
    @UniqueField(entityName = Cliente.class, fieldName = "login")
    private String login;
    @NotBlank @Size(min = 6)
    private String senha;
    private LocalDateTime instanteCadastro;

    public ClienteRequest(String login, String senha) throws NoSuchAlgorithmException {
        this.login = login;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }

    public static String geradorDeHashDaSenha(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes("UTF-8"));
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }

    public Cliente converter(EntityManager entityManager) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new Cliente(login, geradorDeHashDaSenha(senha));
    }
}
