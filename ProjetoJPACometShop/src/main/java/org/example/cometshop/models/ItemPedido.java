package org.example.cometshop.models;

import jakarta.persistence.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;
    @Column(name = "cod_pedido")
    private Long codPedido;
    @Column(name = "cod_produto")
    private Long codProduto;
    private int quantidade;
    private double preco;

    public ItemPedido(Long id, Long codPedido, Long codProduto, int quantidade, double preco) {
        this.id = id;
        this.codPedido = codPedido;
        this.codProduto = codProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedido() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodPedido() {
        return this.codPedido;
    }

    public void setCodPedido(Long codPedido) {
        this.codPedido = codPedido;
    }

    public Long getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "----- ItemPedido -----" +
                "\nID: " + id +
                "\nCódigo do pedido: " + codPedido +
                "\nCódigo do produto: " + codProduto +
                "\nQuantidade: " + quantidade +
                "\nPreço: " + preco;
    }
}
