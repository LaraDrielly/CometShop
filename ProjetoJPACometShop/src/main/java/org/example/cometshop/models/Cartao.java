package org.example.cometshop.models;


import jakarta.persistence.*;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String nomeTitular;
    private String validade;
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}