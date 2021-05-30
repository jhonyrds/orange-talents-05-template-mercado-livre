package br.com.bootcamp.request;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderSimulado {

    public Set<String> enviar(List<MultipartFile> imagens){
        return imagens.stream()
                .map(imagem -> "http://imagem.produto/"
                + imagem.getOriginalFilename() + "/"
                + UUID.randomUUID().toString())
                .collect(Collectors.toSet());
    }
}
