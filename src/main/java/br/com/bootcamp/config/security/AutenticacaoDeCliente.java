package br.com.bootcamp.config.security;

import br.com.bootcamp.model.Cliente;
import br.com.bootcamp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutenticacaoDeCliente implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente =  clienteRepository.findByEmail(username);
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
