package org.example.cometshop.controller;

import org.example.cometshop.models.Produto;
import org.example.cometshop.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "admin/produtos/index"; // página de listagem
    }

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "admin/produtos/form"; // esse HTML que você mandou
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/admin/produtos"; // volta pra listagem
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoRepository.findById(id).orElse(new Produto()));
        return "admin/produtos/form";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/admin/produtos";
    }
}
