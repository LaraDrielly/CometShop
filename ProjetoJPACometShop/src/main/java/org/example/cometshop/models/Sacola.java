package org.example.cometshop.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sacola")
    private Long id;
    @Column(name = "cod_usuario")
    private Long codUsuario;
    @Column(name = "cod_item_pedido")
    private Long codItem;
    private int quantidade;
    @Column(name = "preco_total")
    private double precoTotal;

    public Sacola(Long id, Long codUsuario, Long codItem, int quantidade, double precoTotal) {
        this.id = id;
        this.codUsuario = codUsuario;
        this.codItem = codItem;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public Sacola() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodUsuario() {
        return this.codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Long getCodItem() {
        return this.codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoTotal() {
        return this.precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return "----- Sacola -----" +
                "\nID: " + id +
                "\nCódigo do usuário: " + codUsuario +
                "\nCódigo do item: " + codItem +
                "\nQuantidade: " + quantidade +
                "\nPreço total: " + precoTotal;
    }
}
