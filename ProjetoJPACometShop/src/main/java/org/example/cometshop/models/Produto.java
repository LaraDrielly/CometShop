package org.example.cometshop.models;

import jakarta.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;
    private Long cod_categoria;
    private String nome;
    private String descricao;
    private double preco;

    public Produto(Long id, Long cod_categoria, String nome, String descricao, double preco) {
        this.id = id;
        this.cod_categoria = cod_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCod_categoria() {
        return this.cod_categoria;
    }

    public void setCod_categoria(Long cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "----- Produto -----" +
                "\nID: " + id +
                "\nCódigo da categoria: " + cod_categoria +
                "\nNome: " + nome +
                "\nDescrição: " + descricao +
                "\nPreço: " + preco;
    }
}
