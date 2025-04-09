package org.example.cometshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/adminProduto")
    public String adminProduto() {
        return "areaAdminProduto";
    }

    @GetMapping("/adminCliente")
    public String adminCliente() {
        return "areaAdminCliente";
    }

    @GetMapping("/adminEstoque")
    public String adminEstoque() {
        return "areaAdminEstoque";
    }

    @GetMapping("/adminCategoria")
    public String adminCategoria() {
        return "areaAdminCategoria";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/categoria")
    public String categoria() {
        return "categoriaCabelo";
    }

    @GetMapping("/produto")
    public String produto() {
        return "produto";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento";
    }

    @GetMapping("/sacola")
    public String sacola() {
        return "sacola";
    }

    @GetMapping("/sacola-vazia")
    public String sacolaVazia() {
        return "sacolaVazia";
    }

    @GetMapping("/mensagem")
    public String mensagem() {
        return "msgCompra";
    }

    @GetMapping("/login-admin")
    public String loginAdmin() {
        return "loginAdmin";
    }
}
