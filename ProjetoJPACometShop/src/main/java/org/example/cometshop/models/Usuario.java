package org.example.cometshop.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}