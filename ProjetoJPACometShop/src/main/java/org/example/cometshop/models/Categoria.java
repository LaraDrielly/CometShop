package org.example.cometshop.models;

import jakarta.persistence.*;
import org.example.cometshop.models.Produto;

import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    private String nome;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "----- Categoria -----" +
                "\nID: " + id +
                "\nNome: " + nome ;
    }
}
