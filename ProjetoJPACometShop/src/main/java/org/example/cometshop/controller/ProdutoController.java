package org.example.cometshop.controller;

import org.example.cometshop.models.Produto;
import org.example.cometshop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos
    @GetMapping("")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "areaAdmin/areaAdminProduto";
    }

    // Formulário de novo produto
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "areaAdmin/formProduto";
    }

    // Formulário para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("produto", produto);
        return "areaAdmin/formProduto";
    }

    // Salvar novo ou editar existente
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/admin/produtos";
    }

    // Deletar
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/admin/produtos";
    }
}
