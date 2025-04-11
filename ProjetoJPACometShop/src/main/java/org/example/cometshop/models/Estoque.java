package org.example.cometshop.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Long id;
    @Column(name = "cod_produto")
    private Long codProduto;
    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;
    private int quantidade;
    @Column(name = "data_movimentacao")
    private Date dataMovimentacao;
    private String obsercacao;

    public Estoque(Long id, Long codProduto, String tipoMovimentacao, int quantidade, Date dataMovimentacao, String obsercacao) {
        this.id = id;
        this.codProduto = codProduto;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.obsercacao = obsercacao;
    }

    public Estoque() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getTipoMovimentacao() {
        return this.tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataMovimentacao() {
        return this.dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getObsercacao() {
        return this.obsercacao;
    }

    public void setObsercacao(String obsercacao) {
        this.obsercacao = obsercacao;
    }

    @Override
    public String toString() {
        return "----- Estoque -----" +
                "\nID: " + id +
                "\nCódigo do Produto: " + codProduto +
                "\nTipo da movimentação: " + tipoMovimentacao +
                "\nQuantidade: " + quantidade +
                "\nData da movimentação: " + dataMovimentacao +
                "\nObservação: " + obsercacao;
    }
}
