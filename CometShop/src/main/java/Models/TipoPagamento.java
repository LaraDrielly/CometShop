package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_pagamento")
public class TipoPagamento {
    @Id
    @Column(name = "id_tipo_pagamento")
    private int id_tipo_pagamento;
    private String tpTipo;

    public TipoPagamento() {}

    public TipoPagamento(int id_tipo_pagamento, String tpTipo) {
        this.id_tipo_pagamento = id_tipo_pagamento;
        this.tpTipo = tpTipo;
    }

    public int getId_tipo_pagamento() {
        return id_tipo_pagamento;
    }

    public void setId_tipo_pagamento(int id_tipo_pagamento) {
        this.id_tipo_pagamento = id_tipo_pagamento;
    }

    public String getTpTipo() {
        return tpTipo;
    }

    public void setTpTipo(String tpTipo) {
        this.tpTipo = tpTipo;
    }

    public String toString() {
        return "\nId: " + id_tipo_pagamento +
                "\nTipo de Pagamento: " + tpTipo;
    }
}