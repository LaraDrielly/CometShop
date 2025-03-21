package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @Column(name = "id_carrinho")
    private int id_carrinho;
    private int cod_usuario;

    public Carrinho() {}

    public Carrinho(int id_carrinho, int cod_usuario) {
        this.id_carrinho = id_carrinho;
        this.cod_usuario = cod_usuario;
    }

    public int getId_carrinho() {
        return id_carrinho;
    }

    public void setId_carrinho(int id_carrinho) {
        this.id_carrinho = id_carrinho;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String toString() {
        return "\nId: " + id_carrinho +
                "\nCódigo do Usuário: " + cod_usuario;
    }
}