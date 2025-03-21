package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "url_imagem")
public class UrlImagem {
    @Id
    @Column(name = "id_imagem")
    private int id_imagem;
    private int cod_produto;
    private String uiUrl;

    public UrlImagem() {}

    public UrlImagem(int id_imagem, int cod_produto, String uiUrl) {
        this.id_imagem = id_imagem;
        this.cod_produto = cod_produto;
        this.uiUrl = uiUrl;
    }

    public int getId_imagem() {
        return id_imagem;
    }

    public void setId_imagem(int id_imagem) {
        this.id_imagem = id_imagem;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getUiUrl() {
        return uiUrl;
    }

    public void setUiUrl(String uiUrl) {
        this.uiUrl = uiUrl;
    }

    public String toString() {
        return "\nId: " + id_imagem +
                "\nCódigo do Produto: " + cod_produto +
                "\nUrl da Imagem: " + uiUrl;
    }
}