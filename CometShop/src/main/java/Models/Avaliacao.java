package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @Column(name = "id_avaliacao")
    private int id_avaliacao;
    private int avaliacao;
    private int cod_usuario;
    private int cod_produto;

    public Avaliacao() {}

    public Avaliacao(int id_avaliacao, int avaliacao, int cod_usuario, int cod_produto) {
        this.id_avaliacao = id_avaliacao;
        this.avaliacao = avaliacao;
        this.cod_usuario = cod_usuario;
        this.cod_produto = cod_produto;
    }

    public int getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(int id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String toString() {
        return "\nId: " + id_avaliacao +
                "\nAvaliação: " + avaliacao +
                "\nCódigo do Usuário: " + cod_usuario +
                "\nCódigo do Produto: " + cod_produto;
    }
}