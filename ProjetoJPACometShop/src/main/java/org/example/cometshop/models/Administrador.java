package org.example.cometshop.models;

import jakarta.persistence.*;

@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Long id;
    @Column(name = "nome_completo")
    private String nomeCompleto;
    private String email;
    private String senha;

    private boolean status;

    public Administrador(Long id, String nomeCompleto, String email, String senha, boolean status ) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.status = status;
    }

    public Administrador() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "------- Administrador ------" +
                "\nID: " + id +
                "\nNome completo: " + nomeCompleto +
                "\nEmail: " + email +
                "\nSenha: " + senha +
                "\nStatus: " + status;
    }
}
