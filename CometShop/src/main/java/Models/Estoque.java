package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @Column(name = "id_estoque")
    private int id_estoque;
    private int cod_produto;
    private int estQuantEstoque;

    public Estoque() {}

    public Estoque(int id_estoque, int cod_produto, int estQuantEstoque) {
        this.id_estoque = id_estoque;
        this.cod_produto = cod_produto;
        this.estQuantEstoque = estQuantEstoque;
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public int getQuantEstoque() {
        return estQuantEstoque;
    }

    public void setQuantEstoque(int estQuantEstoque) {
        this.estQuantEstoque = estQuantEstoque;
    }

    public String toString() {
        return "\nId: " + id_estoque +
                "\nCódigo do Produto: " + cod_produto +
                "\nQuantidade em Estoque: " + estQuantEstoque;
    }
}