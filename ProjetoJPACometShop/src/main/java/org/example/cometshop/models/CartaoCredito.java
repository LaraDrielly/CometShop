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

    public CartaoCredito(Long id, Long codUsuario, String numeroCartao, String validade, String cvv, Usuario usuario) {
        this.id = id;
        this.codUsuario = codUsuario;
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.cvv = cvv;
        this.usuario = usuario;
    }

    public CartaoCredito() {

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

    public String getNumeroCartao() {
        return this.numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return this.cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "----- CartaoCredito -----" +
                "\nID: " + id +
                "\nCódigo do usuário: " + codUsuario +
                "\nNúmero do cartão: " + numeroCartao +
                "\nValidade: " + validade +
                "\nCVV: " + cvv +
                "\nUsuário" + usuario;
    }
}
