package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    private int id_usuario;
    private String userNomePerfil;
    private String userSenha;
    private String userEmail;
    private String userNome;
    private String userTel;

    public Usuario() {}

    public Usuario(int id_usuario, String userNomePerfil, String userSenha, String userEmail, String userNome, String userTel) {
        this.id_usuario = id_usuario;
        this.userNomePerfil = userNomePerfil;
        this.userSenha = userSenha;
        this.userEmail = userEmail;
        this.userNome = userNome;
        this.userTel = userTel;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUserNomePerfil() {
        return userNomePerfil;
    }

    public void setUserNomePerfil(String userNomePerfil) {
        this.userNomePerfil = userNomePerfil;
    }

    public String getUserSenha() {
        return userSenha;
    }

    public void setUserSenha(String userSenha) {
        this.userSenha = userSenha;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNome() {
        return userNome;
    }

    public void setUserNome(String userNome) {
        this.userNome = userNome;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String toString() {
        return "\nId: " + id_usuario +
                "\nNome de Perfil: " + userNomePerfil +
                "\nSenha: " + userSenha +
                "\nE-mail: " + userEmail +
                "\nNome completo: " + userNome +
                "\nTel: " + userTel;
    }
}