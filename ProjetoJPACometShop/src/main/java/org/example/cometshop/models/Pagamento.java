package org.example.cometshop.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long id;
    @Column(name = "cod_pedido")
    private Long codPedido;
    private double valor;
    @Column(name = "metodo_pagamento")
    private String tipoPagamento;
    @Column(name = "data_pagamento")
    private Date data;

    public Pagamento(Long id, Long codPedido, double valor, String tipoPagamento, Date data) {
        this.id = id;
        this.codPedido = codPedido;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.data = data;
    }

    public Pagamento() {

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
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getTipoPagamento() {
        return this.tipoPagamento;
    }
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public Date getData() {
        return this.data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "----- Pagamento -----" +
                "\nID: " + id +
                "\nCódigo do Pedido: " + codPedido +
                "\nValor: " + valor +
                "\nTipo de pagamento: " + tipoPagamento +
                "\nData: " + data;
    }
}
