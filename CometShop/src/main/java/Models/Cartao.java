package Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    @Column(name = "id_cartao")
    private int id_cartao;
    private int cod_usuario;
    private long carNumero;
    private int carCvv;
    private Date carDtValidade;

    public Cartao() {}

    public Cartao(int id_cartao, int cod_usuario, long carNumero, int carCvv, Date carDtValidade) {
        this.id_cartao = id_cartao;
        this.cod_usuario = cod_usuario;
        this.carNumero = carNumero;
        this.carCvv = carCvv;
        this.carDtValidade = carDtValidade;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public long getCarNumero() {
        return carNumero;
    }

    public void setCarNumero(long carNumero) {
        this.carNumero = carNumero;
    }

    public int getCarCvv() {
        return carCvv;
    }

    public void setCarCvv(int carCvv) {
        this.carCvv = carCvv;
    }

    public Date getCarDtValidade() {
        return carDtValidade;
    }

    public void setCarDtValidade(Date carDtValidade) {
        this.carDtValidade = carDtValidade;
    }

    public String toString() {
        return "\nId: " + id_cartao +
                "\nCódigo de Usuário: " + cod_usuario +
                "\nNúmero do Cartão: " + carNumero +
                "\nCvv do Cartão: " + carCvv +
                "\nData de Validade do Cartão: " + carDtValidade;
    }
}