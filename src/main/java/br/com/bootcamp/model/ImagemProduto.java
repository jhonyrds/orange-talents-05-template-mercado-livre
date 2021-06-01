package br.com.bootcamp.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @NotNull
    private Produto produto;

    @URL
    @NotBlank
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }
}
