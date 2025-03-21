package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    private int id_categoria;
    private String catCategoria;

    public Categoria() {}

    public Categoria(int id_categoria, String catCategoria) {
        this.id_categoria = id_categoria;
        this.catCategoria = catCategoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCatCategoria() {
        return catCategoria;
    }

    public void setCatCategoria(String catCategoria) {
        this.catCategoria = catCategoria;
    }

    public String toString() {
        return "\nId: " + id_categoria +
                "\nCategoria: " + catCategoria;
    }
}