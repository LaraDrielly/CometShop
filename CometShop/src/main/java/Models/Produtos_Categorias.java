package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos_categorias")
public class Produtos_Categorias {
    @Id
    @Column(name = "id_estoque")
    private int id_produtos_categorias;
    private int cod_categoria;
    private int cod_produto;

    public Produtos_Categorias() {}

    public Produtos_Categorias(int id_produtos_categorias, int cod_categoria, int cod_produto) {
        this.id_produtos_categorias = id_produtos_categorias;
        this.cod_categoria = cod_categoria;
        this.cod_produto = cod_produto;
    }

    public int getId_produtos_categorias() {
        return id_produtos_categorias;
    }

    public void setId_produtos_categorias(int id_produtos_categorias) {
        this.id_produtos_categorias = id_produtos_categorias;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String toString() {
        return "\nId: " + id_produtos_categorias +
                "\nCódigo da Categoria: " + cod_categoria +
                "\nCódigo do Produto: " + cod_produto;
    }
}