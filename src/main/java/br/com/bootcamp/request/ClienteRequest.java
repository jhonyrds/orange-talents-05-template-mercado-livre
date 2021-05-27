package br.com.bootcamp.request;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.util.SenhaLimpa;
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
    @UniqueField(entityName = Cliente.class, fieldName = "email")
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;
    private LocalDateTime instanteCadastro;

    public ClienteRequest(String email, String senha) throws NoSuchAlgorithmException {
        this.email = email;
        this.senha = senha;
    }

    public Cliente converter(EntityManager entityManager) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new Cliente(email, new SenhaLimpa(senha));
    }
}
