package org.example.cometshop.models;

import jakarta.persistence.*;

@Entity
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long id;
    @Column(name = "cod_usuario")
    private Long codUsuario;
    @Column(name = "numero_cartao")
    private String numeroCartao;
    private String validade;
    private String cvv;

    @ManyToOne
    private Usuario usuario;
}
