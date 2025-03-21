package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrinho_produtos")
public class Carrinho_Produtos {
    @Id
    @Column(name = "id_carrinho_produtos")
    private int id_carrinho_produtos;
    private int cod_carrinho;
    private int cod_produto;

    public Carrinho_Produtos() {}

    public Carrinho_Produtos(int id_carrinho_produtos, int cod_carrinho, int cod_produto) {
        this.id_carrinho_produtos = id_carrinho_produtos;
        this.cod_carrinho = cod_carrinho;
        this.cod_produto = cod_produto;
    }

    public int getId_carrinho_produtos() {
        return id_carrinho_produtos;
    }

    public void setId_carrinho_produtos(int id_carrinho_produtos) {
        this.id_carrinho_produtos = id_carrinho_produtos;
    }

    public int getCod_carrinho() {
        return cod_carrinho;
    }

    public void setCod_carrinho(int cod_carrinho) {
        this.cod_carrinho = cod_carrinho;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String toString() {
        return "\nId: " + id_carrinho_produtos +
                "\nCódigo do Carrinho: " + cod_carrinho +
                "\nCod Produto: " + cod_produto;
    }
}