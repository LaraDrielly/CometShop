package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produtos {
    @Id
    @Column(name = "id_produto")
    private int id_produto;
    private int cod_user;
    private String prodDesc;
    private String prodNome;
    private float prodPreco;

    public Produtos() {}

    public Produtos(int id_adm, int cod_user, String prodDesc, String prodNome, float prodPreco) {
        this.id_produto = id_produto;
        this.cod_user = cod_user;
        this.prodDesc = prodDesc;
        this.prodNome = prodNome;
        this.prodPreco = prodPreco;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getCod_user() {
        return cod_user;
    }

    public void setCod_user(int cod_user) {
        this.cod_user = cod_user;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdNome() {
        return prodNome;
    }

    public void setProdNome(String prodNome) {
        this.prodNome = prodNome;
    }

    public float getProdPreco() {
        return prodPreco;
    }

    public void setProdPreco(float prodPreco) {
        this.prodPreco = prodPreco;
    }

    public String toString() {
        return "\nId: " + id_produto +
                "\nCódigo de Usuário: " + cod_user +
                "\nDescrição: " + prodDesc +
                "\nNome: " + prodNome +
                "\nPreço: " + prodPreco;
    }
}