package Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name = "id_pedido")
    private int id_pedido;
    private int cod_carrinho;
    private int cod_tipo_pagamento;
    private Date pedDtPedido;
    private float pedValorTotal;
    private String pedCepUser;
    private String pedBairro;
    private String pedRuaAvenida;
    private String pedComp;
    private int pedNum;
    private long pedTel;

    public Pedido() {}

    public Pedido(int id_pedido, int cod_carrinho, int cod_tipo_pagamento, Date pedDtPedido,
                  float pedValorTotal, String pedCepUser, String pedBairro, String pedRuaAvenida,
                  String pedComp,int pedNum, long pedTel) {
        this.id_pedido = id_pedido;
        this.cod_carrinho = cod_carrinho;
        this.cod_tipo_pagamento = cod_tipo_pagamento;
        this.pedDtPedido = pedDtPedido;
        this.pedValorTotal = pedValorTotal;
        this.pedCepUser = pedCepUser;
        this.pedBairro = pedBairro;
        this.pedRuaAvenida = pedRuaAvenida;
        this.pedComp = pedComp;
        this.pedNum = pedNum;
        this.pedTel = pedTel;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCod_carrinho() {
        return cod_carrinho;
    }

    public void setCod_carrinho(int cod_carrinho) {
        this.cod_carrinho = cod_carrinho;
    }

    public int getCod_tipo_pagamento() {
        return cod_tipo_pagamento;
    }

    public void setCod_tipo_pagamento(int cod_tipo_pagamento) {
        this.cod_tipo_pagamento = cod_tipo_pagamento;
    }

    public Date getPedDtPedido() {
        return pedDtPedido;
    }

    public void setPedDtPedido(Date pedDtPedido) {
        this.pedDtPedido = pedDtPedido;
    }

    public float getPedValorTotal() {
        return pedValorTotal;
    }

    public void setPedValorTotal(float pedValorTotal) {
        this.pedValorTotal = pedValorTotal;
    }

    public String getPedCepUser() {
        return pedCepUser;
    }

    public void setPedCepUser(String pedCepUser) {
        this.pedCepUser = pedCepUser;
    }

    public String getPedBairro() {
        return pedBairro;
    }

    public void setPedBairro(String pedBairro) {
        this.pedBairro = pedBairro;
    }

    public String getPedRuaAvenida() {
        return pedRuaAvenida;
    }

    public void setPedRuaAvenida(String pedRuaAvenida) {
        this.pedRuaAvenida = pedRuaAvenida;
    }

    public String getPedComp() {
        return pedComp;
    }

    public void setPedComp(String pedComp) {
        this.pedComp = pedComp;
    }

    public int getPedNum() {
        return pedNum;
    }

    public void setPedNum(int pedNum) {
        this.pedNum = pedNum;
    }

    public long getPedTel() {
        return pedTel;
    }

    public void setPedTel(long pedTel) {
        this.pedTel = pedTel;
    }

    public String toString() {
        return "\nId: " + id_pedido +
                "\nCódigo do Carrinho: " + cod_carrinho +
                "\nCódigo do Tipo de Pagamento: " + cod_tipo_pagamento +
                "\nData do Pedido: " + pedDtPedido +
                "\nValor Total da Compra: " + pedValorTotal +
                "\nCep de Entrega: " + pedCepUser +
                "\nBairro de Entrega : " + pedBairro +
                "\nRua/Avenida de Entrega: " + pedRuaAvenida +
                "\nComplementos de Entrega: " + pedComp +
                "\nNumero do Endereço de Entrega: " + pedNum +
                "\nTelefone de Contato com Usuário: " + pedTel;
    }
}