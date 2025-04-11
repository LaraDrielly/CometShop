package org.example.cometshop.models;

import jakarta.persistence.*;
import org.example.cometshop.models.ItemPedido;
import org.example.cometshop.models.Usuario;

import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    @Column(name = "cod_usuario")
    private Long codUsuario;
    @Column(name = "data_pedido")
    private Date dataPedido;
    private String status;

    public Pedido(Long id, Long codUsuario, Date dataPedido, String status) {
        this.id = id;
        this.codUsuario = codUsuario;
        this.dataPedido = dataPedido;
        this.status = status;
    }

    public Pedido() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodUsuario() {
        return this.codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Date getDataPedido() {
        return this.dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "----- Pedido -----" +
                "\nID: " + id +
                "\nCódigo do usuário: " + codUsuario +
                "\nData do pedido: " + dataPedido +
                "\nStatus: " + status;
    }
}
