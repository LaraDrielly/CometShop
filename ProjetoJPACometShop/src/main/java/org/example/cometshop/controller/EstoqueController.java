package org.example.cometshop.controller;

import org.example.cometshop.models.Estoque;
import org.example.cometshop.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("movimentacoes", estoqueRepository.findAll());
        return "admin/estoque/index";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("movimentacao", new Estoque());
        return "admin/estoque/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        model.addAttribute("movimentacao", estoque);
        return "admin/estoque/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Estoque movimentacao) {
        estoqueRepository.save(movimentacao);
        return "redirect:/admin/estoque";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        estoqueRepository.deleteById(id);
        return "redirect:/admin/estoque";
    }
}
