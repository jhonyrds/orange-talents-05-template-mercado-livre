package br.com.bootcamp.controller;

import br.com.bootcamp.config.security.GeradorDeToken;
import br.com.bootcamp.controller.dto.TokenDto;
import br.com.bootcamp.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private GeradorDeToken geradorDeToken;

    @PostMapping()
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginRequest request){
        UsernamePasswordAuthenticationToken dadosLogin = request.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = geradorDeToken.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
