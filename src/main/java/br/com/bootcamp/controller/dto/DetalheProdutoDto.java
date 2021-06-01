package br.com.bootcamp.controller.dto;

import br.com.bootcamp.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

public class DetalheProdutoDto {

    private String descricao;
    private String nome;
    private BigDecimal preco;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private double notaMedia;

    public DetalheProdutoDto(Produto produto) {
        this.descricao = produto.getDescricaoProduto();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto
                .mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = produto.mapOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });
        Set<Integer> notas = produto.mapOpinioes(opiniao -> opiniao.getNota());
        IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble average = mapToInt.average();
        if (average.isPresent()) {
            this.notaMedia = average.getAsDouble();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getNotaMedia() {
        return notaMedia;
    }
}
