package org.example.cometshop.controller;

import org.example.cometshop.models.Usuario;
import org.example.cometshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "admin/usuarios/index";
    }

    // Formulário de novo usuário
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuarios/form";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/form";
    }

    // Salvar ou atualizar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios";
    }
}
