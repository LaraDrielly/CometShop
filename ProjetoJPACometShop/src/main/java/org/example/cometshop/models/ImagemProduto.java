package org.example.cometshop.models;

import jakarta.persistence.*;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem")
    private Long id;
    private Long cod_produto;
    private String url;

    public ImagemProduto(Long id, Long cod_produto, String url) {
        this.id = id;
        this.cod_produto = cod_produto;
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCod_produto() {
        return this.cod_produto;
    }

    public void setCod_produto(Long cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "----- ImagemProduto -----" +
                "\nID: " + id +
                "\nCódigo do produto: " + cod_produto +
                "\nURL: " + url;
    }
}
