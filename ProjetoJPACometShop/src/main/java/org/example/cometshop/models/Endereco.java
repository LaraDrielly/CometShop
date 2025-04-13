package org.example.cometshop.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String telefoneContato;
    @ManyToOne
    private Usuario usuario;

    public Endereco(Long id, String rua, String numero, String bairro, String cep, String complemento, String telefoneContato, Usuario usuario) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.telefoneContato = telefoneContato;
        this.usuario = usuario;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefoneContato() {
        return this.telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "----- Endereco -----" +
                "\nID: " + id +
                "\nRua: " + rua +
                "\nNúmero: " + numero +
                "\nBairro: " + bairro +
                "\nCEP: " + cep +
                "\nComplemento: " + complemento +
                "\nTelefone de contato: " + telefoneContato +
                "\nUsuário" + usuario;
    }
}
