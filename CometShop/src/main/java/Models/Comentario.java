package Models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @Column(name = "id_comentario")
    private int id_comentario;
    private int cod_usuario;
    private String comentario;
    private Timestamp comDtComentario;
    private int cod_avaliacao;

    public Comentario() {}

    public Comentario(int id_comentario, int cod_usuario, String comentario, Timestamp comDtComentario, int cod_avaliacao) {
        this.id_comentario = id_comentario;
        this.cod_usuario = cod_usuario;
        this.comentario = comentario;
        this.comDtComentario = comDtComentario;
        this.cod_avaliacao = cod_avaliacao;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Timestamp getComDtComentario() {
        return comDtComentario;
    }

    public void setComDtComentario(Timestamp comDtComentario) {
        this.comDtComentario = comDtComentario;
    }

    public int getCod_avaliacao() {
        return cod_avaliacao;
    }

    public void setCod_avaliacao(int cod_avaliacao) {
        this.cod_avaliacao = cod_avaliacao;
    }

    public String toString() {
        return "\nId: " + id_comentario +
                "\nCódigo do Usuário: " + cod_usuario +
                "\nComentario: " + comentario +
                "\nComDtComentario: " + comDtComentario +
                "\nCod_avaliacao: " + cod_avaliacao;
    }
}