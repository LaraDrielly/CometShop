package org.example.cometshop.controller;

import org.example.cometshop.models.Usuario;
import org.example.cometshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos os usuários
    @GetMapping("")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "areaAdmin/areaAdminUsuario";
    }

    // Formulário de novo usuário
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "areaAdmin/formUsuario";
    }

    // Editar usuário
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/form";
    }

    // Salvar ou atualizar usuário
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios";
    }

    // Excluir usuário
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios";
    }

    // Cadastro de usuário
    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("erro", "E-mail já cadastrado.");
            return "cadastro";
        }

        // Criptografar a senha antes de salvar
        String senhaEncriptada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);

        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    // Página de login
    @GetMapping("/login")
    public String exibirFormularioLogin() {
        return "login"; // Retorna a página de login
    }

    // Processar o login
    @PostMapping("/login")
    public String processarLogin(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null || !new BCryptPasswordEncoder().matches(senha, usuario.getSenha())) {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "login"; // Retorna para a página de login em caso de erro
        }

        return "redirect:/home"; // Redireciona para a página inicial após login bem-sucedido
    }
}
