package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class ADM {
    @Id
    @Column(name = "id_administrador")
    private int id_adm;
    private String admNome;
    private String admEmail;
    private String admSenha;
    private boolean admStatus;

    public ADM() {}

    public ADM(int id_adm, String admNome, String admEmail, String admSenha, boolean admStatus) {
        this.id_adm = id_adm;
        this.admNome = admNome;
        this.admEmail = admEmail;
        this.admSenha = admSenha;
        this.admStatus = admStatus;
    }

    public int getId_adm() {
        return id_adm;
    }

    public void setId_adm(int id_adm) {
        this.id_adm = id_adm;
    }

    public String getAdmNome() {
        return admNome;
    }

    public void setAdmNome(String admNome) {
        this.admNome = admNome;
    }

    public String getAdmEmail() {
        return admEmail;
    }

    public void setAdmEmail(String admEmail) {
        this.admEmail = admEmail;
    }

    public String getAdmSenha() {
        return admSenha;
    }

    public void setAdmSenha(String admSenha) {
        this.admSenha = admSenha;
    }

    public boolean isAdmStatus() {
        return admStatus;
    }

    public void setAdmStatus(boolean admStatus) {
        this.admStatus = admStatus;
    }

    public String toString() {
        return "\nId: " + id_adm +
                "\nNome: " + admNome +
                "\nE-mail: " + admEmail +
                "\nSenha: " + admSenha +
                "\nStatus: " + admStatus;
    }
}