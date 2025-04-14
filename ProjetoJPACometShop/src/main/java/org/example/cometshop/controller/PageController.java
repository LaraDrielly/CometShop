package org.example.cometshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/areaAdminProduto")
    public String adminProduto() {
        return "/areaAdmin/areaAdminProduto";
    }

    @GetMapping("/areaAdminUsuario")
    public String adminUsuario() {
        return "/areaAdmin/areaAdminUsuario";
    }

    @GetMapping("/areaAdminEstoque")
    public String adminEstoque() {
        return "/areaAdmin/areaAdminEstoque";
    }

    @GetMapping("/areaAdminPedido")
    public String adminCategoria() {
        return "/areaAdmin/areaAdminPedido";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/categoriaCabelos")
    public String categoriaCabelos() {
        return "categoriaCabelos";
    }

    @GetMapping("/categoriaPerfumes")
    public String categoriaPerfumes() {
        return "categoriaPerfumes";
    }
    @GetMapping("/categoriaUnhas")
    public String categoriaUnhas() {
        return "categoriaUnhas";
    }
    @GetMapping("/categoriaMaquiagem")
    public String categoriaMaquiagem() {
        return "categoriaMaquiagem";
    }
    @GetMapping("/categoriaSkinCare")
    public String categoriaSkinCare() {
        return "categoriaSkinCare";
    }
    @GetMapping("/categoriaCorpoBanho")
    public String categoriaCorpoBanho() {
        return "categoriaCorpoBanho";
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

    @GetMapping("/sacolaVazia")
    public String sacolaVazia() {
        return "sacolaVazia";
    }

    @GetMapping("/msgCompra")
    public String mensagem() {
        return "msgCompra";
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin() {
        return "loginAdmin";
    }
}
