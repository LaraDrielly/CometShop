package org.example.cometshop.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;

    @ManyToMany
    @JoinTable(
            name = "produtos_categorias",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;
}
