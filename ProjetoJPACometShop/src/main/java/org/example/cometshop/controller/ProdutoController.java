package org.example.cometshop.controller;

import org.example.cometshop.models.Produto;
import org.example.cometshop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.listarTodos());
        return "admin/produtos/index";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "admin/produtos/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.salvar(produto);
        return "redirect:/admin/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoRepository.buscarPorId(id));
        return "admin/produtos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoRepository.excluir(id);
        return "redirect:/admin/produtos";
    }
}