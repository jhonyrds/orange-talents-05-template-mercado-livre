package br.com.bootcamp.controller;

import br.com.bootcamp.controller.dto.ClienteDto;
import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.repository.ClienteRepository;
import br.com.bootcamp.request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/cadastro-cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Cliente cliente = request.converter(clienteRepository);
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }
}
